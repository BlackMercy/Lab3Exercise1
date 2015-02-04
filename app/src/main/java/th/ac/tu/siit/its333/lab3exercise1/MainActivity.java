package th.ac.tu.siit.its333.lab3exercise1;

import android.content.Intent;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;

import java.util.ArrayList;
import java.util.List;


public class MainActivity extends ActionBarActivity {

    int cr = 0;         // Credits
    double gp = 0.0;    // Grade points
    double gpa = 0.0;   // Grade point average

    List<String> listCodes;
    List<Integer> listCredits;
    List<String> listGrades;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        listCodes = new ArrayList<String>();
        listCredits = new ArrayList<Integer>();
        listGrades = new ArrayList<String>();

        //Use listCodes.add("ITS333"); to add a new course code
        //Use listCodes.size() to refer to the number of courses in the list
    }

    public void Calculate(){
        int cr = 0;         // Credits
        double gp = 0.0;    // Grade points
        double gpa = 0.0;   // Grade point average

        for(int i=0;i<listCodes.size();i++)
        {
            String s = listGrades.get(i);
            int a = listCredits.get(i);

            switch(s){
                case "A" : gp += 4 * a; break;
                case "B+" : gp +=3.5 * a; break;
                case "B" : gp += 3 * a; break;
                case "C+" : gp += 2.5 * a; break;
                case "C" : gp += 2 * a; break;
                case "D+" : gp += 1.5 * a; break;
                case "D" : gp += 1 * a; break;
                case "F" : gp += 0 * a; break;
            }

            cr+=a;
        }

    }

    public void buttonClicked(View v) {
        int id = v.getId();

        switch (id) {
            case R.id.button2:
                Intent i = new Intent(this, CourseActivity.class);
                startActivityForResult(i,29);
                break;
            case R.id.button4:
                Intent j = new Intent(this, CourseListActivity.class);

                String s = "List of Courses";
                for (int k=0;k<listCodes.size();k++){
                    s+="\n";
                    s+=listCodes.get(k)+" ";
                    s+="("+listCredits.get(k).toString()+" credits)";
                    s+=" = "+listGrades.get(k);
                }
                j.putExtra("list_of_codes",s);

                startActivity(j);
                break;

            case R.id.button:
                int cr = 0;         // Credits
                double gp = 0.0;    // Grade points
                double gpa = 0.0;

                listCodes = new ArrayList<String>();
                listCredits = new ArrayList<Integer>();
                listGrades = new ArrayList<String>();

                Calculate();
                break;
        }
    }



    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        // Values from child activity
        if (requestCode == 29) {
            if(resultCode == RESULT_OK){
                String courseCode = data.getStringExtra("toAct1");
                int courseCredit = data.getIntExtra("toAct2",0);
                String courseGrade = data.getStringExtra("toAct3");
                listCodes.add(courseCode);
                listCredits.add(courseCredit);
                listGrades.add(courseGrade);

                Calculate();
            }
            else if(resultCode == RESULT_CANCELED){
                //Write your code if there's no result
            }
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}
