package appewtc.masterung.enssystem;

import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import com.jjoe64.graphview.GraphView;
import com.jjoe64.graphview.series.DataPoint;
import com.jjoe64.graphview.series.LineGraphSeries;
import com.squareup.okhttp.OkHttpClient;
import com.squareup.okhttp.Request;
import com.squareup.okhttp.Response;

import org.json.JSONArray;
import org.json.JSONObject;

public class StaticActivity extends AppCompatActivity {

    //Explicit
    private int cat1AnInt, cat2AnInt, cat3AnInt, cat4AnInt;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_static);

        test();

    }   // Main Method

    //Create Inner Class
    public class MySynData extends AsyncTask<Void, Void, String> {

        @Override
        protected String doInBackground(Void... voids) {

            try {

                OkHttpClient okHttpClient = new OkHttpClient();
                Request.Builder builder = new Request.Builder();
                Request request = builder.url("http://swiftcodingthai.com/ens/php_get_addinform.php").build();
                Response response = okHttpClient.newCall(request).execute();
                return response.body().string();

            } catch (Exception e) {
                return null;
            }

        }   // doInBack

        @Override
        protected void onPostExecute(String s) {
            super.onPostExecute(s);

            final String[] categoryStrings = new String[4];
            categoryStrings[0] = "อุบัติเหตุ";
            categoryStrings[1] = "ไฟไหม้";
            categoryStrings[2] = "แผ่นดินไหว";
            categoryStrings[3] = "น้ำท่วม";

            try {

                JSONArray jsonArray = new JSONArray(s);
                for (int i=0;i<jsonArray.length();i++) {

                    JSONObject jsonObject = jsonArray.getJSONObject(i);
                    String strCategory = jsonObject.getString("Type_Inform");

                    if (strCategory.equals(categoryStrings[0])) {
                        cat1AnInt++;
                    } else if (strCategory.equals(categoryStrings[1])) {
                        cat2AnInt++;
                    } else if (strCategory.equals(categoryStrings[2])) {
                        cat3AnInt++;
                    } else {
                        cat4AnInt++;
                    }

                }   // for



            } catch (Exception e) {
                e.printStackTrace();
            }


        }   // onPost

    }   // MySynData


    private void test() {



        int[] intY = new int[4];
        for (int i=0;i<4;i++) {






        }   //for


        GraphView graph = (GraphView) findViewById(R.id.graph);
        LineGraphSeries<DataPoint> series = new LineGraphSeries<DataPoint>(new DataPoint[]{
                new DataPoint(0, 0),
                new DataPoint(1, 5),
                new DataPoint(2, 3),
                new DataPoint(3, 2),
                new DataPoint(4, 6)
        });
        graph.addSeries(series);
    }   // test

}   // Main Class