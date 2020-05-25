import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Objects;

/**
 * The <code>L5</code> class is responsible for creating GUI for gathering
 * parameters and starting {@link DiscoSimulation}.
 * This program is exercise for object oriented programing course at Wroclaw University of Science and Technology.
 * The objective of this list was to create simulation with color-changing rectangles using threads.
 * <p>
 * Main method creates {@link #ParameterWindow()} which intakes 5 values:
 * "Functionality", "Width", "Height", "Speed" and "Probability".
 * <p>
 * @version     24 May 2020
 * @author      Tobiasz Wojnar
 * @see         DiscoSimulation
 */
public class L5 implements ActionListener {

    private final JTextField[] tabTextFiled = new JTextField[4];
    /**
     * List of "functionality" options. Determined by exercise specification.
     */
    private final String[] functionality = new String[]{"3","4","5"};
    private final JComboBox<String> functionalityList  = new JComboBox<>(functionality);

    /**
     * Main method creates {@link #ParameterWindow()}.
     * After clicking run it creates {@link DiscoSimulation}
     */
    public static void main(String[] args) {
        javax.swing.SwingUtilities.invokeLater(() -> new L5().ParameterWindow());
    }

    /**
     * Window for gathering parameters and starting {@link DiscoSimulation}.
     * Non resizable window that intakes 5 values: "Functionality", "Width", "Height", "Speed" and "Probability".
     * Adds listener on "run" button which after clicked creates new {@link DiscoSimulation}
     */
    public void ParameterWindow(){
        JFrame frame = new JFrame("ParameterWindow");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setPreferredSize(new Dimension(400, 150));
        frame.setResizable(false);

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

    /**
     * ActionListener listening for "run" button. Creates new {@link DiscoSimulation}.
     * @param actionEvent action triggering listener, should be equal to "run"
     */
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
