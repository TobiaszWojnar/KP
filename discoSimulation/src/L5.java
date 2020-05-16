import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Objects;

public class L5 implements ActionListener {
    private final JTextField[] tabTextFiled = new JTextField[4];
    String[] functionality = new String[]{"3","4","5"};
    JComboBox<String> functionalityList  = new JComboBox<>(functionality);

    public static void main(String[] args) {
        javax.swing.SwingUtilities.invokeLater(() -> new L5().ParameterWindow());
    }

    public void ParameterWindow(){
        JFrame frame = new JFrame("ParameterWindow");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setPreferredSize(new Dimension(400, 150));

        JPanel panel = new JPanel(new GridLayout(6, 2));
        String[] labels = {"Functionality:","Width - m: ", "Height - n: ", "Speed: ", "Probability: "};

        JLabel l = new JLabel(labels[0], JLabel.TRAILING);
        panel.add(l);
        panel.add(functionalityList);
        for (int i = 0; i+1 < labels.length; i++) {
            l = new JLabel(labels[i+1], JLabel.TRAILING);
            panel.add(l);
            tabTextFiled [i] = new JTextField("10");
            l.setLabelFor(tabTextFiled [i]);
            panel.add(tabTextFiled [i]);
        }

        JButton bRun = new JButton("Run");
        bRun.setActionCommand("run");
        panel.add(bRun);
        bRun.addActionListener(this);

        frame.setContentPane(panel);

        frame.pack();
        frame.setVisible(true);
    }
    @Override
    public void actionPerformed(ActionEvent actionEvent) {
        if ("run".equals(actionEvent.getActionCommand())) {
            int f,m,n,k,p;
            try {
                f=Integer.parseInt((String) Objects.requireNonNull(functionalityList.getSelectedItem()));
                m=Integer.parseInt(tabTextFiled [0].getText());
                n=Integer.parseInt(tabTextFiled [1].getText());
                k=Integer.parseInt(tabTextFiled [2].getText());
                p=Integer.parseInt(tabTextFiled [3].getText());
                if(m>0&&n>0&&k>0&&p>=0&&p<=100){
                    javax.swing.SwingUtilities.invokeLater(() -> new DiscoSimulation(f,m,n,k,p).createAndShowGUI());
                }
            }
            catch (NumberFormatException e0) {
                System.out.println("one parameter is not int");
            }
        }
    }
}
