package com.cooltrickshome;

import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Graphics;
import java.awt.GridLayout;
import java.awt.Rectangle;
import java.awt.Robot;
import java.awt.Toolkit;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.FileInputStream;
import javax.imageio.ImageIO;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import org.apache.http.Header;
import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.mime.MultipartEntity;
import org.apache.http.entity.mime.content.InputStreamBody;
import org.apache.http.impl.client.DefaultHttpClient;

public class ImageResolver
  extends JFrame
  implements MouseListener, MouseMotionListener, Runnable
{
  int drag_status = 0;
  int c1;
  int c2;
  int c3;
  int c4;
  static boolean lock = false;
  
  static
  {
    System.loadLibrary("ImageResolver");
  }
  
  private native int getKeys();
  
  public void showFrame()
    throws Exception
  {
    lock = true;
    Dimension size = Toolkit.getDefaultToolkit().getScreenSize();
    Robot robot = new Robot();
    BufferedImage img = robot.createScreenCapture(new Rectangle(size));
    ImagePanel panel = new ImagePanel(img);
    
    add(panel);
    setLocation(0, 0);
    setSize(size);
    setLayout(new FlowLayout());
    setUndecorated(true);
    setVisible(true);
    addMouseListener(this);
    addMouseMotionListener(this);
    setDefaultCloseOperation(2);
  }
  
  public void upload(File file)
    throws Exception
  {
    final JFrame temp = new JFrame("Searching...");
    Thread t2 = new Thread()
    {
      public void run()
      {
        temp.setSize(250, 0);
        temp.setLayout(new FlowLayout());
        temp.setVisible(true);
        temp.setDefaultCloseOperation(2);
      }
    };
    t2.start();
    
    MultipartEntity entity = new MultipartEntity();
    entity.addPart("encoded_image", new InputStreamBody(new FileInputStream(file), file.getName()));
    
    HttpPost post = new HttpPost("https://www.google.com/searchbyimage/upload");
    post.setEntity(entity);
    
    HttpClient client = new DefaultHttpClient();
    HttpResponse response = client.execute(post);
    String site = response.getFirstHeader("location").getValue();
    Runtime.getRuntime().exec("cmd /c start " + site);
    temp.dispose();
    lock = false;
  }
  
  public void draggedScreen()
    throws Exception
  {
    int w = this.c1 - this.c3;
    int h = this.c2 - this.c4;
    w *= -1;
    h *= -1;
    Robot robot = new Robot();
    BufferedImage img = robot.createScreenCapture(new Rectangle(this.c1, this.c2, w, h));
    File save_path = new File("screen1.jpg");
    ImageIO.write(img, "JPG", save_path);
    dispose();
    upload(save_path);
  }
  
  public synchronized void run()
  {
    for (;;)
    {
      int value = getKeys();
      if ((value == 119) && (lock))
      {
        JOptionPane.showConfirmDialog(
          null, "You can only run one query at a time.Let the previous search complete then you may continue with the new search", "Error", 
          -1);
      }
      else if ((value == 119) && (!lock))
      {
        try
        {
          Thread t2 = new Thread()
          {
            public void run()
            {
              try
              {
                new ImageResolver().showFrame();
              }
              catch (Exception e)
              {
                ImageResolver.lock = false;
                JOptionPane.showConfirmDialog(
                  null, "Some Problem Occured.Please try again", "Error", 
                  -1);
              }
            }
          };
          t2.start();
        }
        catch (Exception e)
        {
          lock = false;
          JOptionPane.showConfirmDialog(
                  null, "Some Problem Occured.Please try again", "Error", 
                  -1);
        }
      }
      else if (value == 120)
      {
        JOptionPane.showConfirmDialog(
          null, "Exiting...", "Exit", 
          -1);
        System.exit(0);
      }
    }
  }
  
  public static void main(String[] args)
  {
    try
    {
      JFrame f1 = new JFrame("ImageResolver Help (Exit the App using F9)");
      JLabel l1 = new JLabel("Please read this before continuing to the program");
      JLabel l2 = new JLabel("Begin Image Search");
      JLabel l3 = new JLabel("To begin Image Search,Open the image and then press F8 key");
      JLabel l4 = new JLabel("A window will open.Just drag the area of the image which you want to search");
      JLabel l5 = new JLabel("After a small delay the search results will be displayed");
      JLabel l6 = new JLabel("------------------------------------------------------------------");
      JLabel l7 = new JLabel("Exit the program");
      JLabel l8 = new JLabel("For exiting the program Press F9.A confirmation message will be displayed and after that application will close.");
      JLabel l9 = new JLabel("------------------------------------------------------------------");
      JLabel l10 = new JLabel("Note: Multiple Searches are not allowed. Please close this window before staring to use the software.");
      JLabel blank = new JLabel("");
      JLabel startinfo7 = new JLabel("Software has been Developed by....");
      JLabel startinfo8 = new JLabel("Anurag Jain");
      JLabel startinfo9 = new JLabel("Software Engineer");
      JLabel startinfo10 = new JLabel("(cs.anurag.jain@gmail.com)");
      JLabel startinfo11 = new JLabel("Project Homepage: https://cooltrickshome.blogspot.in");
      JLabel startinfo12 = new JLabel("For any problems or feedback you may contact me directly at cs.anurag.jain@gmail.com");
      
      f1.add(l1);
      f1.add(l2);
      f1.add(l3);
      f1.add(l4);
      f1.add(l5);
      f1.add(l6);
      f1.add(l7);
      f1.add(l8);
      f1.add(l9);
      f1.add(l10);
      f1.add(blank);
      f1.add(blank);
      f1.add(blank);
      f1.add(startinfo7);
      f1.add(startinfo8);
      f1.add(startinfo9);
      f1.add(startinfo10);
      f1.add(startinfo11);
      f1.add(startinfo12);
      f1.setLayout(new GridLayout(19, 1));
      f1.setVisible(true);
      f1.setSize(700, 700);
      f1.setDefaultCloseOperation(2);
      
      Thread t1 = new Thread(new ImageResolver());
      t1.start();
    }
    catch (Exception e)
    {
      JOptionPane.showConfirmDialog(
        null, "Some Problem Occured.Please try again", "Error", 
        -1);
    }
  }
  
  public void mouseClicked(MouseEvent arg0) {}
  
  public void mouseEntered(MouseEvent arg0) {}
  
  public void mouseExited(MouseEvent arg0) {}
  
  public void mousePressed(MouseEvent arg0)
  {
    repaint();
    this.c1 = arg0.getX();
    this.c2 = arg0.getY();
  }
  
  public void mouseReleased(MouseEvent arg0)
  {
    repaint();
    if (this.drag_status == 1)
    {
      this.c3 = arg0.getX();
      this.c4 = arg0.getY();
      try
      {
        draggedScreen();
      }
      catch (Exception e)
      {
        e.printStackTrace();
      }
    }
  }
  
  public void mouseDragged(MouseEvent arg0)
  {
    repaint();
    this.drag_status = 1;
    this.c3 = arg0.getX();
    this.c4 = arg0.getY();
  }
  
  public void mouseMoved(MouseEvent arg0) {}
  
  public void paint(Graphics g)
  {
    super.paint(g);
    int w = this.c1 - this.c3;
    int h = this.c2 - this.c4;
    w *= -1;
    h *= -1;
    if (w < 0) {
      w *= -1;
    }
    g.drawRect(this.c1, this.c2, w, h);
  }
}

