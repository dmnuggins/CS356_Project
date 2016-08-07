import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * Created by CesarRecinos on 8/7/2016.
 */
public class adminGui extends JFrame{
    private JPanel rootPanel;
    private JPanel employeePanel;
    private JPanel buttonPanel;
    private JButton employeeButton;
    private JPanel meetingPanel;
    private JButton meetingButton;
    private JButton backPanel;
    private JButton meetingRooms;
    private JButton goBack;

    public adminGui() {

        super("Admin Menu");
        setContentPane(rootPanel);

        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        rootPanel.add(buttonPanel);

        buttonPanel.add(employeePanel);
        buttonPanel.add(meetingPanel);
        buttonPanel.add(backPanel);

        employeePanel.add(employeeButton);
        meetingPanel.add(meetingRooms);
        backPanel.add(goBack);

        employeeButton.addActionListener(new ActionListener(){
            public void actionPerformed(ActionEvent e) {

            }
        });


        meetingRooms.addActionListener(new ActionListener(){
            public void actionPerformed(ActionEvent e) {

                roomDisplayAdminGui roomDisplay =new roomDisplayAdminGui();
                roomDisplay.setVisible(true);

            }
        });

        goBack.addActionListener(new ActionListener(){
            public void actionPerformed(ActionEvent e) {

            }
        });


        setVisible(true);
    }
}
