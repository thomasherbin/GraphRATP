package graphDraw;

public interface DrawSettings {
     int frameWidth = 1000;
     int frameHeight = 1000;

     int minLat = 800000;
     int maxLat = 900000;
     int minLon = 200000;
     int maxLon = 450000;

     int stationRadius = 5;

     String [][] colorLigne = {
             {"1", "#FFCD00"},
             {"2", "#003CA6"},
             {"3", "#837902"},
             {"3B", "#6EC4E8"},
             {"4", "#CF009E"},
             {"5", "#FF7E2E"},
             {"6", "#6ECA97"},
             {"7", "#FA9ABA"},
             {"7B", "#6ECA97"},
             {"8", "#E19BDF"},
             {"9", "#B6BD00"},
             {"10", "#C9910D"},
             {"11", "#704B1C"},
             {"12", "#007852"},
             {"13", "#6EC4E8"},
             {"14", "#62259D"},
     };

}
