/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ditherimage;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.Font;
import javafx.application.Platform;
import javafx.embed.swing.JFXPanel;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.StackPane;
import javax.swing.JApplet;
import javax.swing.JFrame;
import static javax.swing.JFrame.EXIT_ON_CLOSE;
import javax.swing.JLabel;
import javax.swing.SwingUtilities;
import javax.swing.UIManager;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.net.MalformedURLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.imageio.ImageIO;

import javax.swing.border.Border;
import javax.swing.filechooser.FileNameExtensionFilter;

/**
 *
 * @author shath
 */
public class DitherImage extends JApplet {

    static String path;

    private static final int JFXPANEL_WIDTH_INT = 300;
    private static final int JFXPANEL_HEIGHT_INT = 250;
    private static JFXPanel fxContainer;

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {

        JFrame frame = new JFrame("Dither Image");
        JPanel panel = new JPanel();
        panel.setLayout(null);

        JLabel title = new JLabel("Dither Image");
        title.setFont(new Font("Arial", Font.PLAIN, 30));
        title.setSize(300, 40);
        title.setLocation(500, 20);
        panel.add(title);

        Border border = BorderFactory.createLineBorder(Color.BLACK, 3);

        JButton button1 = new JButton("Load Image");
        //  button1.setBackground(Color.pink);
        button1.setFont(new Font("Arial", Font.PLAIN, 30));
        button1.setSize(300, 70);
        button1.setLocation(150, 120);
        //  button1.addActionListener(this);
        panel.add(button1);

        JLabel input_img = new JLabel("     Input Image");
        input_img.setFont(new Font("Arial", Font.PLAIN, 25));
        input_img.setBorder(border);
        input_img.setOpaque(true);
        input_img.setBackground(Color.pink);
        input_img.setSize(200, 70);
        input_img.setLocation(180, 270);
        panel.add(input_img);

        JLabel img = new JLabel();
        img.setOpaque(true);
        img.setBackground(Color.pink);
        img.setSize(400, 400);
        img.setLocation(100, 400);
        panel.add(img);

        JLabel bit1 = new JLabel("   Bits for Red");
        bit1.setFont(new Font("Arial", Font.PLAIN, 20));
        bit1.setOpaque(true);
        bit1.setBackground(Color.pink);
        bit1.setBorder(border);
        bit1.setSize(150, 50);
        bit1.setLocation(660, 100);
        panel.add(bit1);

        JLabel bit2 = new JLabel("  Bits for Green");
        bit2.setFont(new Font("Arial", Font.PLAIN, 20));
        bit2.setOpaque(true);
        bit2.setBackground(Color.pink);
        bit2.setBorder(border);
        bit2.setSize(150, 50);
        bit2.setLocation(810, 100);
        panel.add(bit2);

        JLabel bit3 = new JLabel("   Bits for Blue");
        bit3.setFont(new Font("Arial", Font.PLAIN, 20));
        bit3.setOpaque(true);
        bit3.setBackground(Color.pink);
        bit3.setBorder(border);
        bit3.setSize(150, 50);
        bit3.setLocation(960, 100);
        panel.add(bit3);

        JTextField field1 = new JTextField();
        field1.setFont(new Font("Arial", Font.PLAIN, 20));
        field1.setBorder(border);
        field1.setSize(150, 50);
        field1.setLocation(660, 148);
        field1.setHorizontalAlignment(JTextField.CENTER);
        panel.add(field1);

        JTextField field2 = new JTextField();
        field2.setFont(new Font("Arial", Font.PLAIN, 20));
        field2.setBorder(border);
        field2.setSize(150, 50);
        field2.setLocation(810, 148);
        field2.setHorizontalAlignment(JTextField.CENTER);
        panel.add(field2);

        JTextField field3 = new JTextField();
        field3.setFont(new Font("Arial", Font.PLAIN, 20));
        field3.setBorder(border);
        field3.setSize(150, 50);
        field3.setLocation(960, 148);
        field3.setHorizontalAlignment(JTextField.CENTER);
        panel.add(field3);

        JButton button2 = new JButton(" Output Image");
        // button2.setBackground(Color.pink);
        button2.setFont(new Font("Arial", Font.PLAIN, 20));
        button2.setSize(200, 70);
        button2.setLocation(770, 270);
        panel.add(button2);

        JLabel img2 = new JLabel();
        img2.setOpaque(true);
        img2.setBackground(Color.pink);
        img2.setSize(400, 400);
        img2.setLocation(700, 400);
        panel.add(img2);

        JLabel note = new JLabel("  NOTE : ");
        note.setFont(new Font("Arial", Font.PLAIN, 20));
        note.setOpaque(true);
        note.setBackground(Color.LIGHT_GRAY);
        note.setSize(700, 80);
        note.setLocation(100, 850);
        panel.add(note);

        JButton reset = new JButton("Reset");
        reset.setFont(new Font("Arial", Font.PLAIN, 20));
        reset.setSize(150, 50);
        reset.setLocation(880, 860);
        panel.add(reset);

        frame.setVisible(true);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.pack();
        frame.add(panel);
        frame.setSize(1200, 1000);
        frame.setContentPane(panel);

        ////////// upload image
        button1.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(java.awt.event.ActionEvent e) {

                JFileChooser file = new JFileChooser();
                file.setCurrentDirectory(new File(System.getProperty("user.home")));

                FileNameExtensionFilter filter = new FileNameExtensionFilter("*.Images", "jpg", "png");
                file.addChoosableFileFilter(filter);
                int result = file.showSaveDialog(null);

                if (result == JFileChooser.APPROVE_OPTION) {
                    File selectedFile = file.getSelectedFile();
                    path = selectedFile.getAbsolutePath();
                }

                ImageIcon MyImage = new ImageIcon(path);
                Image image1 = MyImage.getImage();
                ImageIcon image = new ImageIcon(image1);

                img.setIcon(image);
            }

        });

        button2.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(java.awt.event.ActionEvent e) {

                String text1 = field1.getText();
                int red = Integer.parseInt(text1);

                String text2 = field2.getText();
                int green = Integer.parseInt(text2);

                String text3 = field3.getText();
                int blue = Integer.parseInt(text3);
                int sum = red + green + blue;

                BufferedImage dietered = null;
                if (sum <= 8 && sum >= 0) {
                    try {
                        //  ImageIcon imageIcon = new ImageIcon((dietered));
                        dietered = floydSteinbergDithering(ImageIO.read(new File(path)), red, green, blue);
                    } catch (MalformedURLException ex) {
                        Logger.getLogger(DitherImage.class.getName()).log(Level.SEVERE, null, ex);
                    } catch (IOException ex) {
                        Logger.getLogger(DitherImage.class.getName()).log(Level.SEVERE, null, ex);
                    }

                    ImageIcon imageIcon = new ImageIcon(new ImageIcon(dietered).getImage());
                    img2.setIcon(imageIcon); // imge2.setImage(imageIcon);
                    note.setText(" NOTE :");

                }
                if (sum >= 9) {
                    note.setText(" NOTE : The sum of red & green & blue SHOULD be equal or less than 8");
                }
            }

        });

        reset.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(java.awt.event.ActionEvent e) {
                img.setIcon(null);
                img2.setIcon(null);

                field1.setText("");
                field2.setText("");
                field3.setText("");
                note.setText("  NOTE : ");

            }

        });

    }

    private static BufferedImage floydSteinbergDithering(BufferedImage img, int r, int g, int b) {

        C3[] palette = new C3[]{
            new C3(r, g, b),
            new C3(r, g, 255),
            new C3(r, 255, b),
            new C3(r, 255, 255),
            new C3(255, g, b),
            new C3(255, g, 255),
            new C3(255, 255, b),
            new C3(255, 255, 255)
        };

        int w = img.getWidth();
        int h = img.getHeight();

        C3[][] d = new C3[h][w];

        for (int y = 0; y < h; y++) {
            for (int x = 0; x < w; x++) {
                d[y][x] = new C3(img.getRGB(x, y));
            }
        }

        for (int y = 0; y < img.getHeight(); y++) {
            for (int x = 0; x < img.getWidth(); x++) {

                C3 oldColor = d[y][x];
                C3 newColor = findClosestPaletteColor(oldColor, palette);
                img.setRGB(x, y, newColor.toColor().getRGB());

                C3 err = oldColor.sub(newColor);

                if (x + 1 < w) {
                    d[y][x + 1] = d[y][x + 1].add(err.mul(7. / 16));
                }
                if (x - 1 >= 0 && y + 1 < h) {
                    d[y + 1][x - 1] = d[y + 1][x - 1].add(err.mul(3. / 16));
                }
                if (y + 1 < h) {
                    d[y + 1][x] = d[y + 1][x].add(err.mul(5. / 16));
                }
                if (x + 1 < w && y + 1 < h) {
                    d[y + 1][x + 1] = d[y + 1][x + 1].add(err.mul(1. / 16));
                }
            }
        }

        return img;
    }

    private static C3 findClosestPaletteColor(C3 c, C3[] palette) {
        C3 closest = palette[0];

        for (C3 n : palette) {
            if (n.diff(c) < closest.diff(c)) {
                closest = n;
            }
        }

        return closest;
    }

}
