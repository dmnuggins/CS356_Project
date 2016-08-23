package meetingapp.gui;
import meetingapp.entity.Employee;
import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * Created by CesarRecinos on 8/9/2016.
 */
public class CreateRoomGUI extends MeetingAppGUI {
    private JPanel rootPanel;
    private JTextField numberField;
    private JTextField capacityField;
    private JLabel roomIDLabel;
    private JLabel capacityLabel;
    private JButton saveButton;
    private JButton cancelButton;

    public CreateRoomGUI(final Employee employee){
        super("Create a New Room", employee);
        setContentPane(rootPane);
        pack();


        numberField.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

            }
        });
        capacityField.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

            }
        });
        saveButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

            }
        });
        cancelButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

            }
        });
    }
}
