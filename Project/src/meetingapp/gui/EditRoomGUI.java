package meetingapp.gui;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * Created by CesarRecinos on 8/16/2016.
 */
public class EditRoomGUI {
    private JPanel rootPanel;
    private JLabel capacityLabel;
    private JLabel roomIDLabel;
    private JTextField capacityField;
    private JTextField numberField;
    private JButton saveButton;
    private JButton cancelButton;
    private JButton deleteButton;

    public EditRoomGUI() {
        deleteButton.addActionListener(new ActionListener() {
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
        capacityField.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

            }
        });
    }
}
