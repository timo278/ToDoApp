import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class ToDoAppGui extends JFrame {
    public ToDoAppGui() {
        // set gui and add a title
        super("ToDoApp");

        // close an app on button
        setDefaultCloseOperation(EXIT_ON_CLOSE);

        // set the size of gui
        setSize(450, 600);

        // load gui at the center of the screen
        setLocationRelativeTo(null);

        // make layout
        setLayout(null);

        // prevent resize
        setResizable(false);

        // show gui components
        guiComponents();
    }

    protected void guiComponents() {
        /*
            1 text field is used to enter a new task
            2 button is used to create a new task (to database)
         */
        // create a text field
        JTextField note = new JTextField();

        // set the location of the text field
        note.setBounds(40, 45, 350, 50);

        // show the text field on the screen
        add(note);

        JButton button = new JButton();

        button = getJButton(note);

        // show the button on the screen
        add(button);

    }

    private JButton getJButton(JTextField note) {
        JButton button = new JButton("create a new task");

        // set the location of the button
        button.setBounds(40, 100, 150, 40);

        button.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // get a note
                String newNote = note.getText();

                if(validateNote(newNote)) {
                    // add a note to database
                    ConnectToPostgres.insertNoteIntoDatabase(newNote);

                    JOptionPane.showMessageDialog(ToDoAppGui.this,
                            "Task is created");
                }
                else {
                    JOptionPane.showMessageDialog(ToDoAppGui.this,
                            "You need to enter a task");
                }
            }
        });
        return button;
    }

    private boolean validateNote(String note) {
        // check if note is empty
        if(note.isEmpty()) {
            return false;
        }
        return true;
    }
}
