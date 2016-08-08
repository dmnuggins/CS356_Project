import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;


/**
 * Created by CesarRecinos on 8/7/2016.
 */
public class RoomDisplayAdminGui extends JFrame {
    private JPanel rootPanel;
    private JPanel textPanel;
    private JPanel buttonPanel;
    private JButton createRoom;
    private JButton editRoom;
    private JButton cancelButton;
    private JComboBox listComboBox;

    public RoomDisplayAdminGui(){
        super("Meeting Rooms");
        setContentPane(rootPanel);
        pack();

        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);


        listComboBox.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

            }
        });
        createRoom.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

            }
        });

        editRoom.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

            }
        });

        cancelButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                dispose();

                new AdminGUI().setVisible(true);
            }
        });

        setVisible(true);
    }
}
