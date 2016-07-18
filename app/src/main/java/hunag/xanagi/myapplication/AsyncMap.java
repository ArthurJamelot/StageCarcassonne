package hunag.xanagi.myapplication;

import android.content.Context;
import android.os.AsyncTask;
import java.sql.*;
import org.osmdroid.util.GeoPoint;

import java.util.ArrayList;


public class AsyncMap extends AsyncTask<Context, Void, ArrayList<GeoPoint>> {

    private MainActivity listener;
    static Connection connection;

    public AsyncMap(MainActivity ecouteur) {
        listener = ecouteur;

    }

    @Override
    protected ArrayList<GeoPoint> doInBackground(Context... mapViews) {
        GeoPoint bureau = new GeoPoint(43.211840, 2.352902);
        GeoPoint maison = new GeoPoint(43.215130, 2.351041);
        ArrayList<GeoPoint> points = new ArrayList<>();
        points.add(bureau);
        points.add(maison);
        try {
            try {
                Class.forName("com.mysql.jdbc.Driver");
            } catch (Exception e) {
                e.printStackTrace();
            }
            connection = DriverManager.getConnection("jdbc:mysql://db4free.net:3306/teststagemairieb","maroane","stagemairie");
            PreparedStatement statement = connection.prepareStatement("SELECT * FROM `Hotspot` ");
            ResultSet rs = statement.executeQuery();
            rs.beforeFirst();
            while (rs.next()) {
                System.out.println(rs.getString("title_hotspot"));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return points;
    }

    @Override
    protected void onPostExecute(ArrayList<GeoPoint> points) {
        listener.mapCreation(points);
    }
}
