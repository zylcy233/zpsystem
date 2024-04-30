package com.zpsystem.service;



import com.zpsystem.entity.Emptbl;

import java.util.ArrayList;
import java.util.HashMap;

public interface EmptblService {
    boolean insertEmptbl(Emptbl emptbl);
    boolean deleteEmptbl(HashMap<String, String> m);
    boolean updateEmptbl(HashMap<String, String> m);
    boolean selectEmptbl(Emptbl emptbl);
    boolean checkEmptbl(HashMap<String, String> m);

    ArrayList<Emptbl> getEmptbls(HashMap<String, String> map);
}
