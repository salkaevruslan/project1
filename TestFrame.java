
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.io.IOException;

public class TestFrame extends JFrame {

    private JTextField textField;
    private JTextField textField2;
    private JPanel panel = new JPanel();
    private boolean isBranch = true;

    public TestFrame() {
        super("PhotoMath");
        createGUI();
    }

    private ImageIcon createImageIcon(String path) {
        java.net.URL imgURL = getClass().getResource(path);
        if ((imgURL != null) && (!path.equals(""))) {
            return new ImageIcon(imgURL, "");
        } else {
            JOptionPane.showMessageDialog(TestFrame.this,
                    new String[]{"Изображение отсутствует",
                            "Выберите другой файл"
                    }, "Ошибка", JOptionPane.ERROR_MESSAGE);
            return null;
        }
    }

    private Image iconToImage(Icon icon) {
        if (icon instanceof ImageIcon) {
            return ((ImageIcon) icon).getImage();
        } else {
            BufferedImage image = new BufferedImage(icon.getIconWidth(), icon.getIconHeight(), BufferedImage.TYPE_INT_RGB);
            icon.paintIcon(null, image.getGraphics(), 0, 0);
            return image;
        }
    }

    public void createGUI() {

        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        panel.setLayout(null);

        JButton button3 = new JButton("Выберите изображение");
        button3.setPreferredSize(new Dimension(600, 200));
        ImageIcon icon = createImageIcon("example.jpg");
        Icon button3Icon = icon;
        button3.setVerticalTextPosition(AbstractButton.BOTTOM);
        button3.setHorizontalTextPosition(AbstractButton.CENTER);
        button3.setIcon(button3Icon);
        button3.setEnabled(true);
        button3.setVisible(true);
        button3.setBounds(5, 5, 600, 200);
        panel.add(button3);

        JButton button1 = new JButton("");
        button1.setPreferredSize(new Dimension(25, 25));
        Icon bIcon = UIManager.getIcon("OptionPane.informationIcon");
        Image img1 = iconToImage(bIcon);
        Image newimg1 = img1.getScaledInstance(25, 25, java.awt.Image.SCALE_SMOOTH);
        Icon button1Icon = new ImageIcon(newimg1);
        button1.setIcon(button1Icon);
        button1.setEnabled(true);
        button1.setVisible(true);
        button1.setBounds(610, 5, 25, 25);
        panel.add(button1);

        textField = new JTextField("Ваш пример:");
        textField.setToolTipText("Проверьте правильно ли считано задание");
        textField.setColumns(40);
        textField.setVisible(false);
        textField.setBounds(5, 220, 600, 25);
        panel.add(textField);

        JLabel imageLabel = new JLabel("Ваше изображение");
        imageLabel.setVerticalTextPosition(JLabel.TOP);
        imageLabel.setHorizontalTextPosition(JLabel.CENTER);
        imageLabel.setVisible(false);
        imageLabel.setEnabled(false);

        JButton button2 = new JButton("Посчитать:");
        button2.setPreferredSize(new Dimension(600, 100));
        Icon button2Icon = UIManager.getIcon("OptionPane.goIcon");
        button2.setVerticalTextPosition(AbstractButton.BOTTOM);
        button2.setHorizontalTextPosition(AbstractButton.CENTER);
        button2.setIcon(button2Icon);
        button2.setVisible(false);
        button2.setEnabled(false);
        button2.setBounds(5, 255, 600, 200);
        panel.add(button2);

        textField2 = new JTextField("Результат:");
        textField2.setColumns(40);
        textField2.setName("Результат");
        textField2.setVisible(false);
        textField2.setBounds(5, 475, 600, 25);
        panel.add(textField2);

        JLabel label1 = new JLabel("Ваш пример", null, SwingConstants.RIGHT);
        label1.setBounds(240, 205, 100, 15);
        label1.setVisible(false);
        label1.setEnabled(false);
        panel.add(label1);

        JLabel label2 = new JLabel("Результат", null, SwingConstants.RIGHT);
        label2.setBounds(230, 455, 100, 15);
        label2.setVisible(false);
        label2.setEnabled(false);
        panel.add(label2);
        panel.updateUI();

        button3.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                button2.setVisible(false);
                button2.setEnabled(false);
                textField2.setVisible(false);
                textField.setVisible(false);
                imageLabel.setIcon(null);
                imageLabel.setVisible(false);
                imageLabel.setEnabled(false);
                label1.setVisible(false);
                label1.setEnabled(false);
                label2.setVisible(false);
                label2.setEnabled(false);
                panel.updateUI();
                String result = JOptionPane.showInputDialog(
                        TestFrame.this,
                        "<html><h2>Введите имя файла");
                try {
                    ImageIcon icon1 = null;
                    int sizeIc = 200;
                    if ((result.length() <= 4) || (!result.substring(result.length() - 4, result.length()).equals(".jpg")))
                        icon1 = createImageIcon("" + result + ".jpg");
                    else
                        icon1 = createImageIcon("" + result);
                    Image img = icon1.getImage();
                    Image newimg = img.getScaledInstance(sizeIc * icon1.getIconWidth() / icon1.getIconHeight(), sizeIc, java.awt.Image.SCALE_SMOOTH);
                    icon1 = new ImageIcon(newimg);
                    Icon labelIcon = icon1;
                    imageLabel.setIcon(labelIcon);
                    imageLabel.setBounds(640, 5, 0 + icon1.getIconWidth(), 18 + icon1.getIconHeight());
                    imageLabel.setVisible(true);
                    imageLabel.setEnabled(true);
                    panel.add(imageLabel);
                    int result1 = JOptionPane.showConfirmDialog(
                            TestFrame.this,
                            "Есть ли в вашем примере скобки?",
                            "Важный вопрос",
                            JOptionPane.YES_NO_OPTION);
                    // Окна подтверждения c 2-мя параметрами
                    if (result1 == JOptionPane.YES_OPTION)
                        isBranch = true;
                    else if (result1 == JOptionPane.NO_OPTION)
                        isBranch = false;
                    try {
                        if ((result.length() <= 4) || (!result.substring(result.length() - 4, result.length()).equals(".jpg")))
                            textField.setText(test2resizeimages.image(result + ".jpg", isBranch));
                        else
                            textField.setText(test2resizeimages.image(result, isBranch));
                    } catch (IOException e1) {
                        //😠
                    }
                    textField.setVisible(true);
                    textField.setEnabled(true);
                    label1.setVisible(true);
                    label1.setEnabled(true);
                    button2.setVisible(true);
                    button2.setEnabled(true);
                    panel.updateUI();
                } catch (NullPointerException ex1) {
                    System.out.println();
                }
            }
        });

        button2.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                try {
                    textField2.setVisible(true);
                    textField2.setEnabled(true);
                    label2.setVisible(true);
                    label2.setEnabled(true);
                    String s = textField.getText();
                    //System.out.println(s);
                    textField2.setText(Double.toString(Solution.result(s)));
                    panel.updateUI();
                } catch (Exception e2) {
                    textField2.setVisible(false);
                    textField2.setEnabled(false);
                    JOptionPane.showMessageDialog(TestFrame.this,
                            new String[]{"Неправильное выражение",
                                    "Выберите другой файл или исправьте вручную"
                            }, "Ошибка", JOptionPane.ERROR_MESSAGE);
                }
                panel.updateUI();
            }
        });

        button1.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                JOptionPane.showMessageDialog(TestFrame.this,
                        new String[]{"-Для максимально точной работы используйте шрифт 72+",
                                "-Лучше писать стандартными шрифтами(к примеру Colibri)",
                                "-Знаки и числа не должны налезать на друга",
                                "-Не стоит загружать и изображения больше 5000X5000",
                                "-Пишите умножение как 'x',а не как '*'",
                                "-Использовать формат jpg"
                        }, "Полезная информация", JOptionPane.INFORMATION_MESSAGE);

            }
        });
        getContentPane().add(panel);
        setPreferredSize(new Dimension(800, 600));
    }

    public static void main(String[] args) {
        javax.swing.SwingUtilities.invokeLater(new Runnable() {
            public void run() {
                JFrame.setDefaultLookAndFeelDecorated(true);
                TestFrame frame = new TestFrame();
                frame.pack();
                frame.setLocationRelativeTo(null);
                frame.setVisible(true);
            }
        });
    }
}
