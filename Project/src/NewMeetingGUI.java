import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * Created by Dylan Nguyen on 8/8/2016.
 */
public class NewMeetingGUI extends JFrame{
    private JPanel newMeetingPanel;
    private JPanel northPanel;
    private JPanel southPanel;
    private JPanel titlePanel;
    private JPanel comboBoxPanel;
    private JComboBox roomComboBox;
    private JComboBox startDateComboBox;
    private JComboBox endDateComboBox;
    private JLabel roomLabel;
    private JLabel startDateLabel;
    private JLabel endDateLabel;
    private JButton Create;
    private JButton backButton;


    public NewMeetingGUI() {
        super("newMeetingPanel");
        backButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

            }
        });
    }

    public void showGUI() {
        setTitle("New Meeting");
        setContentPane(new NewMeetingGUI().newMeetingPanel);
        pack();
        setVisible(true);
        setLocationRelativeTo(null);

    }


}
