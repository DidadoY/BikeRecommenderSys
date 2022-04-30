import javax.swing.*;
import javax.swing.plaf.basic.BasicScrollBarUI;
import java.awt.*;
import java.util.HashMap;

public class mainWindow {
    private static JFrame frame;
    private JPanel panel;
    private JList list1;

    private JButton backButton;

    private JScrollPane scrollPane;
    public mainWindow(HashMap<Integer, MotorBike> bikes, MotorBikeSet mbs) {
        scrollPane.getVerticalScrollBar().setUI(new BasicScrollBarUI() {
            @Override
            protected void configureScrollBarColors() {
                this.trackColor = new Color(118,119,187);
                this.thumbColor  = new Color(58,80,194);
            }
        });
        scrollPane.getHorizontalScrollBar().setUI(new BasicScrollBarUI() {
            @Override
            protected void configureScrollBarColors() {
                this.trackColor = new Color(118,119,187);
                this.thumbColor  = new Color(58,80,194);
            }
        });
        scrollPane.getVerticalScrollBar().getComponent(0).setBackground(new Color(118,119,187));
        scrollPane.getVerticalScrollBar().getComponent(1).setBackground(new Color(58,80,194));

        DefaultListModel demoList = new DefaultListModel();

        for(Integer key : bikes.keySet()) {
            String l = "";
            l += mbs.getMotorBike(key).getName();
            demoList.addElement(l);
        }
        list1.setModel(demoList);
    }
    public void showWindow(int x, int y) {
        frame = new JFrame("Sistema Recomanador");
        frame.setContentPane(this.panel);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.pack();
        frame.setBounds(x, y, 600, 600);
        frame.setResizable(false);
        frame.setVisible(true);
    }
}
