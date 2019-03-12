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

    public void start() {
        String fileName = "./src/com/W.LAS";
        File file = new File(fileName);

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
                    lock = 0;
                    break;
                }

                if( lock == 0 ) {
                    Float[][] numbers = new Float[getNumb()][];
                    List<String> l = new ArrayList<String>();
                    String[] x = line.split(" ");
                    for (String q : x ) {
                        if ( Integer.valueOf(q) > 0 ) {
                            l.add( q );
                        }
                    }

                    for (int i = 0; i < getNumb(); i++) {
                        numbers[i][y] = Float.valueOf(l.get(i));
                    }
                    y++;
                }
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }

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
