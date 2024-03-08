package basics.service;

public class SuggestInsuranceProvider {
    public static void main(String[] args) {
    String bajajAllianzInurance[]={"health checkup","tax benefits","hospitalization costs"};
    String kotakMahindraInsurance[]={"premium","alternative treatment","health checkup"};
    String reliance[]={"health checkup","premium","hospitalization cost"};
    for(String each:args){
        for(String feature:bajajAllianzInurance){
            if(each.equalsIgnoreCase(feature))
                System.out.println("Bajaj Allianz Inurance Offers: "+feature);
        }
    }
    for(String each:args){
        for(String feature:kotakMahindraInsurance){
            if(each.equalsIgnoreCase(feature))
                System.out.println("Kotak Mahindra Insurance Offers: "+feature);
        }
    }
    for(String each:args){
        for(String feature:reliance){
            if(each.equalsIgnoreCase(feature))
                System.out.println("Reliance Offers: "+feature);
        }
    }
 }
    //        String newFeature="",suggestedCompany="";
//        Scanner s = new Scanner(System.in);
//        System.out.println("Enter the feature");
//        newFeature=s.nextLine();
//        for(int index=0;index<bajajAllianzInurance.length;index++)
//        {
//            if(newFeature.toLowerCase().contains(bajajAllianzInurance[index]))
//                suggestedCompany+="bajajAllianzInurance ";
//
//        }
//        for(int index=0;index<kotakMahindraInsurance.length;index++)
//        {
//            if(newFeature.toLowerCase().contains(kotakMahindraInsurance[index]))
//                suggestedCompany+="kotakMahindraInsurance ";
//
//        }
//        for(int index=0;index<reliance.length;index++)
//        {
//            if(newFeature.toLowerCase().contains(reliance[index]))
//                suggestedCompany+="reliance ";
//
//        }
//        System.out.println("Companies Suggeste dbased on given features:"+suggestedCompany);
//        s.close();
//    }
}


