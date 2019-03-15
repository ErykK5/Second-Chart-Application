package data;

import java.io.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;


public class ReadData {

    private float strt;
    private float stop;
    private float step;
    private int numb;
    private List<String> arr;
    private Float[][] values;
    private float nullVal;
    private File file;
    private String path;

    public ReadData(File file) {
        this.file = file;
    }

    public ReadData(String path) {
        this.path = path;
    }

    public ReadData() {
    }

    public void start() {
        File file = this.file;
        if( !file.exists() ) {
            System.out.println("File not found!");
        }

        int y = 0;
        try (BufferedReader br = new BufferedReader(new FileReader(file))) {
            String line;
            int lock = 1;
            while ((line = br.readLine()) != null) {
                if (line.contains("STRT.M")) {
                    String s = line.substring(6);
                    s = s.replace(":","");
                    s = s.replace(" ","");
                    setStrt(Float.valueOf(s));
                }
                if (line.contains("STOP.M")) {
                    String s = line.substring(6);
                    s = s.replace(":","");
                    s = s.replace(" ","");
                    setStop(Float.valueOf(s));
                }
                if (line.contains("STEP.M")) {
                    String s = line.substring(6);
                    s = s.replace(":","");
                    s = s.replace(" ","");
                    setStep(Float.valueOf(s));
                }
                if (line.contains("NULL.")) {
                    String s = line.substring(6);
                    s = s.replace(":","");
                    s = s.replace(" ","");
                    setNullVal(Float.valueOf(s));
                }

                if (line.contains("~A")) {
                    String s = line.substring(2);
                    String[] x = s.split(" ");


                    int count = 0;
                    List<String> values = new ArrayList<String>();
                    for(String data: x) {
                        if( data.length() > 0 ) {
                            values.add(data);
                            count++;
                        }
                    }

                    setNumb(count);
                    setArr(values);
                }

                if (line.contains("#") && line.contains("M") && !line.contains("E")) {
                    break;      //read param
                }
            }

            Float[][] numbers = new Float[getNumb()][(int)((getStop()-getStrt())/getStep()+2)];

            while ((line = br.readLine()) != null) {
                List<String> l = new ArrayList<String>();
                String[] x = line.split(" ");
                for (String q : x ) {
                    if (q.length() > 0) {
                        l.add( q );
                    }
                }

                for (int i = 0; i < getNumb(); i++) {
                    numbers[i][y] = Float.valueOf(l.get(i));
                }
                y++;
            }
            setValues(numbers);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public float getNullVal() {
        return nullVal;
    }

    public void setNullVal(float nullVal) {
        this.nullVal = nullVal;
    }

    public Float[][] getValues() {
        return values;
    }

    public void setValues(Float[][] values) {
        this.values = values;
    }

    public List<String> getArr() {
        return arr;
    }

    public void setArr(List<String> arr) {
        this.arr = arr;
    }

    public int getNumb() {
        return numb;
    }

    public void setNumb(int numb) {
        this.numb = numb;
    }

    public float getStrt() {
        return strt;
    }

    public void setStrt(float strt) {
        this.strt = strt;
    }

    public float getStop() {
        return stop;
    }

    public void setStop(float stop) {
        this.stop = stop;
    }

    public float getStep() {
        return step;
    }

    public void setStep(float step) {
        this.step = step;
    }
}
