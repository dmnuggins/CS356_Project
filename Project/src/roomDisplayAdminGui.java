import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;


/**
 * Created by CesarRecinos on 8/7/2016.
 */
public class roomDisplayAdminGui extends JFrame {
    private JPanel rootPanel;
    private JPanel textPanel;
    private JPanel buttonPanel;
    private JButton createRoom;
    private JButton editRoom;
    private JButton goBack;
    private JComboBox listComboBox;

    public roomDisplayAdminGui(){
        super("Meeting Rooms");
        setContentPane(rootPanel);

        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        rootPanel.add(textPanel);
        rootPanel.add(buttonPanel);

        textPanel.add(listComboBox);

        buttonPanel.add(createRoom);
        buttonPanel.add(editRoom);
        buttonPanel.add(goBack);

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

        goBack.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

            }
        });

        setVisible(true);
    }
}
