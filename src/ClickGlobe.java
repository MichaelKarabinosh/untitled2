import gov.nasa.worldwind.BasicModel;
import gov.nasa.worldwind.awt.WorldWindowGLCanvas;
import gov.nasa.worldwind.event.SelectEvent;
import gov.nasa.worldwind.event.SelectListener;
import gov.nasa.worldwind.geom.Position;

import javax.swing.*;
import java.awt.*;

public class ClickGlobe extends JFrame {

    public ClickGlobe() {
        setTitle("Click the Globe");
        setSize(800, 600);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);

        WorldWindowGLCanvas wwd = new WorldWindowGLCanvas();
        wwd.setPreferredSize(new Dimension(800, 600));
        wwd.setModel(new BasicModel());

        // Add a click listener
        wwd.addSelectListener(new SelectListener() {
            @Override
            public void selected(SelectEvent event) {
                if (event.getEventAction().equals(SelectEvent.LEFT_CLICK)) {
                    Position pos = wwd.getCurrentPosition();
                    if (pos != null) {
                        double lat = pos.getLatitude().degrees;
                        double lon = pos.getLongitude().degrees;
                        JOptionPane.showMessageDialog(wwd,
                                "Latitude: " + lat + "\nLongitude: " + lon,
                                "Clicked Position", JOptionPane.INFORMATION_MESSAGE);
                    } else {
                        System.out.println("No position picked.");
                    }
                }
            }
        });

        getContentPane().add(wwd, BorderLayout.CENTER);
        pack();
        setVisible(true);
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> new ClickGlobe());
    }
}
