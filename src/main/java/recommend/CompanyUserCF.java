package recommend;
import dao.CompanyDao;
import dao.VideoDao;

import java.util.*;

public class CompanyUserCF {
    Map<Integer, Integer> userItemLength;//存储每一个用户对应的不同物品总数  eg: A 3
    Map<Integer, Set<Integer>> itemUserCollection;//建立物品到用户的倒排表 eg: a A B
    Set<Integer> items;
    Integer recommendUser;
    int N = 6500;
    int[][] sparseMatrix = new int[N][N];
    Map<Integer, Double> result = new HashMap<>();

    public CompanyUserCF(Map<Integer, Integer> userItemLength, Map<Integer, Set<Integer>> itemUserCollection, Set<Integer> items, Integer recommendUser) {
        this.userItemLength = userItemLength;
        this.itemUserCollection = itemUserCollection;
        this.items = items;
        this.recommendUser = recommendUser;
    }

    public CompanyUserCF() {
    }

    public ArrayList<Integer> UserCF(){
        Set<Map.Entry<Integer, Set<Integer>>> entrySet = this.itemUserCollection.entrySet();
        Iterator<Map.Entry<Integer, Set<Integer>>> iterator = entrySet.iterator();
        ArrayList<Integer> companies = new ArrayList<>();
        while(iterator.hasNext()) {
            Set<Integer> commonUsers = iterator.next().getValue();
            for (Integer user_u : commonUsers) {
                for (Integer user_v : commonUsers) {
                    if (user_u.equals(user_v)) {
                        continue;
                    }
                    sparseMatrix[user_u][user_v] += 1;//计算用户u与用户v都有正反馈的物品总数
                }
            }
        }
        if(this.userItemLength.get(recommendUser)!=null){
            for(Integer item: this.items){//遍历每一件物品
                Set<Integer> users = this.itemUserCollection.get(item);//得到购买当前物品的所有用户集合
                if(!users.contains(recommendUser)){//如果被推荐用户没有购买当前物品，则进行推荐度计算
                    double itemRecommendDegree = 0.0;
                    for(Integer user: users){
                        itemRecommendDegree += sparseMatrix[recommendUser][user]/Math.sqrt(this.userItemLength.get(recommendUser)*this.userItemLength.get(user));//推荐度计算
                    }
                    this.result.put(item,itemRecommendDegree);
                }
            }
            Map<Integer, Double> sortMap = sortMap(result);
            for (Integer key : sortMap.keySet()) {
                companies.add(key);
            }
        }
        while(companies.size()<10){
            CompanyDao companyDao = new CompanyDao();
            companies.addAll(companyDao.selectTOP());
        }
        return companies;
    }
    public static Map<Integer, Double> sortMap(Map<Integer, Double> map) {
        //利用Map的entrySet方法，转化为list进行排序
        List<Map.Entry<Integer, Double>> entryList = new ArrayList<>(map.entrySet());
        //利用Collections的sort方法对list排序
        Collections.sort(entryList, new Comparator<Map.Entry<Integer, Double>>() {
            @Override
            public int compare(Map.Entry<Integer, Double> o1, Map.Entry<Integer, Double> o2) {
                //正序排列，倒序反过来
                if(o1.getValue() - o2.getValue()>0){
                    return -1;
                }
                else{
                    return 1;
                }
            }
        });
        //遍历排序好的list，一定要放进LinkedHashMap，因为只有LinkedHashMap是根据插入顺序进行存储
        LinkedHashMap<Integer, Double> linkedHashMap = new LinkedHashMap<Integer, Double>();
        for (Map.Entry<Integer, Double> e : entryList
        ) {
            linkedHashMap.put(e.getKey(),e.getValue());
        }
        return linkedHashMap;
    }
}
