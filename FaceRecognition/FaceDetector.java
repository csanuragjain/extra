package com.cooltrickshome;

/**
 * Reference:
 * https://github.com/sarxos/webcam-capture/tree/master/webcam-capture-examples/webcam-capture-detect-face
 * http://openimaj.org/
 */
import java.awt.FlowLayout;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.Iterator;
import java.util.List;
import javax.swing.JFrame;

import org.openimaj.image.FImage;
import org.openimaj.image.ImageUtilities;
import org.openimaj.image.processing.face.detection.DetectedFace;
import org.openimaj.image.processing.face.detection.HaarCascadeDetector;

import com.github.sarxos.webcam.Webcam;
import com.github.sarxos.webcam.WebcamResolution;


public class FaceDetector extends JFrame {

	private static final long serialVersionUID = 1L;

	private static final HaarCascadeDetector detector = new HaarCascadeDetector();
	private Webcam webcam = null;
	private BufferedImage img= null;
	private List<DetectedFace> faces = null;

	public FaceDetector() throws IOException {
		
		webcam = Webcam.getDefault();
		webcam.setViewSize(WebcamResolution.VGA.getSize());
		webcam.open(true);
		img=webcam.getImage();
		webcam.close();
		ImagePanel panel=new ImagePanel(img);
		panel.setPreferredSize(WebcamResolution.VGA.getSize());
		add(panel);
		
		setTitle("Face Recognizer");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		pack();
		setLocationRelativeTo(null);
		setVisible(true);
	}
	
	public void detectFace(){
		JFrame fr=new JFrame("Discovered Faces");
		faces = detector.detectFaces(ImageUtilities.createFImage(img));
		if (faces == null) {
			System.out.println("No faces found in the captured image");
			return;
		}

		Iterator<DetectedFace> dfi = faces.iterator();
		while (dfi.hasNext()) {
			DetectedFace face = dfi.next();
			FImage image1 = face.getFacePatch();
			ImagePanel p=new ImagePanel(ImageUtilities.createBufferedImage(image1));
			fr.add(p);
		}
		fr.setLayout(new FlowLayout(0));
		fr.setSize(500,500);
		fr.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		fr.setVisible(true);
	}
	
	public static void main(String[] args) throws IOException {
		new FaceDetector().detectFace();
	}
}