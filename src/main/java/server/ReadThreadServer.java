package server;

import util.LoginDTO;
import util.NetworkUtil;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class ReadThreadServer implements Runnable {
    private final Thread thr;
    private final NetworkUtil networkUtil;
    private final Server server;
    public HashMap<String,NetworkUtil>companyNetworkMap;
    public HashMap<String, String> userMap;


    public ReadThreadServer(HashMap<String, NetworkUtil> companyNetworkMap, HashMap<String, String> map, NetworkUtil networkUtil, Server server) {
        this.server=server;
        this.companyNetworkMap=companyNetworkMap;
        this.userMap = map;
        this.networkUtil = networkUtil;
        this.thr = new Thread(this);
        thr.start();
    }

    public void run() {
        try {
            while (true) {
                Object o = networkUtil.read();
                if (o != null) {
                    if (o instanceof LoginDTO) {
                        LoginDTO loginDTO = (LoginDTO) o;
                        companyNetworkMap.put(loginDTO.getUserName(), networkUtil);
                        String password = userMap.get(loginDTO.getUserName());
                        loginDTO.setStatus(loginDTO.getPassword().equals(password));
                        if(loginDTO.isStatus()) {
                            List<Movie> result = new ArrayList<>();
                            for (Movie m : server.getMovieList()) {
                                if (m.getCompany().equalsIgnoreCase(loginDTO.getUserName())) {
                                    result.add(m);
                                }
                            }
                            loginDTO.setCompanyMovieList(result);
                        }

                        networkUtil.write(loginDTO);
                    }
                    else if(o instanceof TransferMovie)
                    {
                        TransferMovie obj=(TransferMovie) o;
                        String sender =obj.getSender();
                        String receiver=obj.getReceiver();
                        for(Movie m: server.getMovieList())
                        {
                            if(m.getName().equalsIgnoreCase(obj.getMovie().getName()))
                            {
                                m.setCompany(obj.getReceiver());

                            }
                        }
                        //server.getMovieList().add(obj.getMovie());
                        NetworkUtil nu=companyNetworkMap.get(receiver);
                        if(nu!=null)
                        {
                            nu.write(obj);
                        }

                    }
                    else if(o instanceof Movie)
                    {
                        Movie obj=(Movie) o;
                        server.getMovieList().add(obj);
                        networkUtil.write(obj);
                    }
                }
            }
        } catch (Exception e) {
            System.out.println(e);
        } finally {
            try {
                networkUtil.closeConnection();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
}



