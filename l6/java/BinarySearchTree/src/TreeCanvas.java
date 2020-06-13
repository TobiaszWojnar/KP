import javax.swing.*;
import java.awt.*;

/**
 * The <code>TreeCanvas</code> class is panel on which {@link GenericBinaryTree binary tree} is painted.
 * It is exercise for object oriented programing course at Wroclaw University of Science and Technology.
 * The objective of this list was to implement binary search tree with generic type keys,
 * create {@link TreeGUI GUI  application} and implement {@link MyClient client} {@link MyServer server} architecture.
 * <p>
 *      <code>TreeCanvas</code> extends <code>JPanel</code> paints {@link GenericBinaryTree binary tree} nodes and edges
 *      and resize itself to contain all nodes. Method {@link TreeCanvas#treeUpdate(String) treeUpdate} intakes key indexes and values, rescales canvas and repaints.
 *      Root has index 1, every left child has index equal to twice as its parent and right child equals to twice plus one. Index number and key are separated by " " and nodes by "\t"
 * <p/>
 * @version     09 June 2020
 * @author      Tobiasz Wojnar
 * @see         TreeGUI
 * @see         TreeSerialization
 */
public class TreeCanvas extends JPanel {
    private String[] keys;
    private int[] indexes;
    private int treeHeight;
    private int canvasHeight;
    private int canvasWidth;
    private final int r;
    private final Color backgroundColor = new Color(238,238,238);

    /**
     * Constructor of TreeCanvas, sets value of radius and calls {@link TreeCanvas#treeUpdate(String) treeUpdate} method.
     * @value r Radius equal 20
     */
    public TreeCanvas(){
        r=20;
        treeUpdate("");
    }

    /**
     * Clears canvas and paints tree nodes.
     * @see  TreeCanvas#paintElement(Graphics, int)
     */
    public void paint(Graphics g){
        g.setColor(backgroundColor);
        g.fillRect(0, 0, canvasWidth, canvasHeight);
        g.setColor(Color.BLACK);

        if(treeHeight!=0) {
            for (int i = 0; i < keys.length; i++)
                paintElement(g, i);
        }
    }

    /**
     * Paints node of tree.
     * For each element it paints oval,
     * its value (cropped by {@link #shrink(String)} method if its longer than 6 characters)
     * and edge between its and its parent (not for root).
     * @param number Index of element in keys and indexes table.
     */
    private void paintElement(Graphics g, int number){
        int offsetY = canvasHeight /(treeHeight);
        int index=indexes[number];
        int y=1;
        int x=0;

        for(int k=index;k>1;k=k/2)
            y++;
        int numberOfNodesInRow= (int) Math.pow(2,y-1);
        for(int k=numberOfNodesInRow; k<index; k++)
            x++;
        int offsetX = canvasWidth /(numberOfNodesInRow*2);

        if(y>1) {
            int additionalOffset = offsetX - r;
            if(index%2==1)
                additionalOffset=-additionalOffset;
            g.drawLine(
                    offsetX * (1 + x * 2),
                    offsetY * (y) - (3 * r) / 2 - offsetY / 2,
                    offsetX * (1+x * 2)+additionalOffset,
                    offsetY * (y-1) + (3*r)/2 - offsetY / 2);
        }
        g.drawOval(
                offsetX*(1+x*2)-2*r,
                offsetY*(y)-r-offsetY/2,
                4 * r,
                2 * r);
        g.drawString(shrink(keys[number]),
                offsetX*(1+x*2)-shrink(keys[number]).length()*3-4,
                offsetY*(y)-offsetY/2+r/4);
    }

    /**
     * If key is longer than 6 it shrinks it.
     * Replacing middle characters by ".."
     * @param key to be shrink if to long (length>6)
     * @return shrunk key
     */
    private String shrink(String key){
        if(key.length()>6)
            return key.substring(0,3)+".."+key.substring(key.length()-3);
        return key;
    }

    /**
     * Updates tables containing nodes, tree height, canvas dimensions and repaints tree.
     * @param input key indexes and values
     *              Root has index 1, every left child has index equal to twice as its parent
     *              and right child equals to twice plus one.
     *              Index number and key are separated by " " and nodes by "\t"
     * @see #getTreeHeight()
     * @see #resizeCanvas()
     */
    public void treeUpdate(String input){
        if(input!=null&&!input.isEmpty()) {
            String[] nodes = input.split("\t");
            String[] temp;
            indexes = new int[nodes.length];
            keys = new String[nodes.length];
            for (int i = 0; i < nodes.length; i++) {
                temp = nodes[i].split(" ");
                indexes[i] = Integer.parseInt(temp[0]);
                keys[i] = temp[1];
            }
        }else {
            indexes = null;
            keys = null;
        }
        getTreeHeight();
        resizeCanvas();
        setSize(new Dimension(canvasWidth,canvasHeight));
        setPreferredSize(new Dimension(canvasWidth,canvasHeight));
        repaint();
    }

    /**
     * Searches for deepest leaf, and calculates height.
     * For empty tree sets {@link #treeHeight} = 0
     */
    private void getTreeHeight(){
        if(indexes!=null) {
            int tempHeight;
            int height = 0;
            for (int index : indexes) {
                tempHeight = 1;
                for (int tempIndex = index; tempIndex > 1; tempIndex = tempIndex / 2)
                    tempHeight++;
                if (tempHeight > height)
                    height = tempHeight;
            }
            treeHeight = height;
        }else
            treeHeight=0;
    }

    /**
     * Calculates minimal and set it {@link #canvasWidth} and {@link #canvasHeight}.
     * <code>canvasWidth</code> will be calculates for leafs not to go on top of each other but not less than 540 px.
     * <code>canvasHeight</code> will be calculates for making distance between nodes easy to read but not less than 275 px.
     */
    private void resizeCanvas(){
        canvasWidth = (int) (4.2*r*Math.pow(2,treeHeight-1));
        if(canvasWidth<540)
            canvasWidth=540;
        canvasHeight= (treeHeight+1)*(4*r);
        if(canvasHeight<275)
            canvasHeight=275;
    }
}