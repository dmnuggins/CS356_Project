package meetingapp.gui;

import meetingapp.entity.*;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;
import java.util.Date;

/**
 * Created by Dylan Nguyen on 8/8/2016.
 */
public class CreateMeetingEmployeeGUI extends MeetingAppGUI{
    private JPanel rootPanel;


    public CreateMeetingEmployeeGUI(final Employee employee) {
        super("New Meeting", employee);
        setup(rootPanel);

        this.employee = employee;
        //get all employees
       /** allRooms = Room.getAll();
        for (int i = 0; i < allRooms.size(); i++) {
            roomComboBox.addItem(Integer.toString(allRooms.get(i).getID()));
        }

        DefaultTableModel model = new DefaultTableModel();
        inviteListTable.setModel(model);

        model.addColumn("Name");
        model.addRow(new Object[] { employee.getName() });

        meetingDateLabel.setText(new Date().toLocaleString());

        backButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                new EmployeeGUI(employee);
                dispose();
            }
        });*/




        setVisible(true);
    }

}
