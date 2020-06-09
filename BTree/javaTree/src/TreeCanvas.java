import javax.swing.*;
import java.awt.*;

//TODO documentation
public class TreeCanvas extends JPanel {
    String[] nodes;
    String[] keys;
    int[] indexes;
    int treeHeight;
    int canvasHeight;
    int canvasWidth;
    int r;
    Color backgroundColor = new Color(238,238,238);

    public TreeCanvas(){
        r=20;
        keys=null;
        indexes=null;
        getTreeHeight();
        resizeCanvas();
    }

    public void paint(Graphics g){
        g.setColor(backgroundColor);
        g.fillRect(0, 0, canvasWidth, canvasHeight);
        g.setColor(Color.BLACK);

        if(treeHeight!=0) {
            for (int i = 0; i < keys.length; i++)
                paintElement(g, i);
        }
    }
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

    private String shrink(String key){
        if(key.length()>6)
            return key.substring(0,3)+".."+key.substring(key.length()-3);
        return key;
    }
    public void treeUpdate(String input){
        if(input!=null&&!input.isEmpty()) {
            nodes = input.split("\t");
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
    private void resizeCanvas(){
        canvasWidth = (int) (4.2*r*Math.pow(2,treeHeight-1));
        if(canvasWidth<540)
            canvasWidth=540;
        canvasHeight= (treeHeight+1)*(4*r);
        if(canvasHeight<275)
            canvasHeight=275;
    }
}