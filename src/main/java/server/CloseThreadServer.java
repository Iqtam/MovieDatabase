package server;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.util.Scanner;

public class CloseThreadServer implements Runnable {
    Server server;
    Thread th;
    private static final String OUTPUT_FILE_NAME="newmovies.txt";
    CloseThreadServer(Server server)
    {
        this.server=server;
        th=new Thread(this);
        th.start();
    }
    public  void writeFile(String OUTPUT_FILE_NAME)throws Exception
        {
        BufferedWriter bw = new BufferedWriter(new FileWriter(OUTPUT_FILE_NAME));
        for(Movie m:server.getMovieList())
        {
            bw.write(m.getName()+",");
            bw.write(m.getRelease_year()+",");
            bw.write(m.getGenre1()+",");
            bw.write(m.getGenre2()+",");
            bw.write(m.getGenre3()+",");
            bw.write(m.getRunning_time()+",");
            bw.write(m.getCompany()+",");
            bw.write(m.getBudget()+",");
            bw.write(Long.toString(m.getRevenue()));
            bw.write(System.lineSeparator());
        }
        bw.close();
    }
    @Override
    public void run() {
        System.out.println("Write exit to stop server :");
        Scanner scn=new Scanner(System.in);
        while(scn.hasNextLine())
        {
            //System.out.println("Write exit to stop server :");
            if(scn.nextLine().equalsIgnoreCase("exit"))
            {
                try {
                    writeFile(OUTPUT_FILE_NAME);
                } catch (Exception e) {
                    throw new RuntimeException(e);
                }
                System.exit(1);
                break;
            }
        }
    }
}
