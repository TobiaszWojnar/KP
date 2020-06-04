import java.awt.*;

public class TreeCanvas extends Canvas {
    String input;
    String[] nodes;
    String[] keys;
    int[] indexes;
    int treeHeight;
    public TreeCanvas(){

    }
    public void paint(Graphics g){
        Graphics2D g2d = (Graphics2D) g;

        for(int i=0; i<nodes.length;i++) {
            //g2d.drawOval();
            //g2d.drawString();
            System.out.println("node "+keys[i]);
            if(indexes[i]>0) {
                //g2d.drawLine();
                System.out.println("edge from "+indexes[i]+" to "+indexes[i]/2);
            }

        }

    }
    public void treeUpdate(String input){
        this.input=input;
        nodes=input.split("\t");
        String[] temp;
        for(int i=0; i<nodes.length;i++){
            temp=nodes[i].split(" ");
            indexes[i]=Integer.parseInt(temp[0]);
            keys[i]=temp[1];
        }
        treeHeight=getTreeHeight();
        repaint();
    }
    private int getTreeHeight(){
        int tempHeight;
        int height=0;
        for (int index : indexes) {
            tempHeight = 1;
            for (int tempIndex = index; tempIndex > 1; tempIndex = tempIndex / 2) {
                tempHeight++;
            }
            if (tempHeight > height)
                height = tempHeight;
        }
        return height;
    }
    //public Dimension getDimensions(){ }
}
