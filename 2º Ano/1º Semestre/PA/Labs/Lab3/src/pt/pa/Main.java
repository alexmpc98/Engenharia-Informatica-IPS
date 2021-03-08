package pt.pa;

import pt.pa.model.*;

public class Main {

    public static void main(String[] args) {

        CourseGrades grades = generate_example();
        grades.changeStatistic(new StatisticMedian());
        System.out.println("Median - " + grades.computeStatistic());

        grades.changeStatistic(new StatisticArithmeticAvg());
        System.out.println("Average - " + grades.computeStatistic());
        grades.changeStatistic(new StatisticHighestGrade());
        System.out.println("Highest Grade - " + (int)grades.computeStatistic());
        grades.changeStatistic(new StatisticLowestGrade());
        System.out.println("Lowest Grade - " + (int)grades.computeStatistic());

        // Por nome - omissão
        //System.out.println(grades);
        // Por id
        //grades.changeSorting(new SortById());
        //System.out.println(grades);
        // Por nota (grade) - Mais pequena para a mais alta
        //grades.changeSorting(new SortByGradeLowestToHighest());
        //System.out.println(grades);
        // Por nota (grade) - Mais alta para a mais pequena
        //grades.changeSorting(new SortByGradeHighestToLowest());
        //System.out.println(grades);

    }

    private static CourseGrades generate_example() {
        CourseGrades instance = new CourseGrades("Programação Avançada");

        instance.add( new StudentGrade("996693","Jada Head",17));
        instance.add( new StudentGrade("372930","Tad Mccall",18));
        instance.add( new StudentGrade("948974","Roanna Cameron",9));
        instance.add( new StudentGrade("547279","Davis Fernandez",7));
        instance.add( new StudentGrade("835363","Leo Blair",15));
        instance.add( new StudentGrade("714856","Belle Williams",14));
        instance.add( new StudentGrade("212310","Talon Anderson",6));
        instance.add( new StudentGrade("861677","Desiree Hodge",19));
        instance.add( new StudentGrade("725325","Howard Dickson",15));
        instance.add( new StudentGrade("161575","Hilel Downs",6));
        instance.add( new StudentGrade("164881","Callie Pearson",11));
        instance.add( new StudentGrade("858359","Dora Case",20));
        instance.add( new StudentGrade("735063","Karina Myers",9));
        instance.add( new StudentGrade("716795","Aristotle Fitzpatrick",13));
        instance.add( new StudentGrade("786999","Cullen Sargent",11));
        instance.add( new StudentGrade("995329","Sarah Lang",13));
        instance.add( new StudentGrade("620038","Lee Hewitt",10));
        instance.add( new StudentGrade("568516","Yoshi Watkins",12));
        instance.add( new StudentGrade("431985","Nomlanga Bryan",10));
        instance.add( new StudentGrade("228516","Yolanda Watkins",8));
        instance.add( new StudentGrade("503048","Francesca Sexton",12));
        instance.add( new StudentGrade("744187","Dieter Moody",17));
        instance.add( new StudentGrade("891861","Allistair Newton",16));
        instance.add( new StudentGrade("877548","Raven Lowery",6));
        instance.add( new StudentGrade("459369","Hamish Rosario",18));
        instance.add( new StudentGrade("980896","Orson Chavez",17));
        instance.add( new StudentGrade("882380","Matthew Melendez",6));
        instance.add( new StudentGrade("513080","Brady Wilkins",6));
        instance.add( new StudentGrade("646101","Madonna Klein",18));
        instance.add( new StudentGrade("301057","Fay Durham",18));
        instance.add( new StudentGrade("559460","Aspen Frederick",7));
        instance.add( new StudentGrade("397809","Mari Scott",12));
        instance.add( new StudentGrade("948409","Marsden Kaufman",11));
        instance.add( new StudentGrade("570641","Alexa Baxter",20));
        instance.add( new StudentGrade("798543","Evan Keller",16));
        instance.add( new StudentGrade("948013","Bertha Conner",18));
        instance.add( new StudentGrade("446135","Alexa Ortiz",13));
        instance.add( new StudentGrade("984147","Gareth Meyer",12));
        instance.add( new StudentGrade("494585","Maris Burch",18));
        instance.add( new StudentGrade("302903","Caesar Wyatt",14));
        instance.add( new StudentGrade("814539","Dora Gill",15));
        instance.add( new StudentGrade("280866","Tatum Haley",13));
        instance.add( new StudentGrade("861388","Faith Sparks",7));
        instance.add( new StudentGrade("883894","Clare Hill",7));
        instance.add( new StudentGrade("527081","Elmo Olsen",8));
        instance.add( new StudentGrade("810728","Alika Fulton",16));
        instance.add( new StudentGrade("543091","Rooney Barrett",16));
        instance.add( new StudentGrade("625036","Nasim Macias",11));
        instance.add( new StudentGrade("654849","Macey Pacheco",13));
        instance.add( new StudentGrade("180967","Candace Davis",10));
        instance.add( new StudentGrade("643074","Zephania Frost",7));
        instance.add( new StudentGrade("657802","Summer Cruz",20));
        instance.add( new StudentGrade("968961","Isaac Suarez",18));
        instance.add( new StudentGrade("316111","Amery Jenkins",19));
        instance.add( new StudentGrade("375768","Malik Jennings",13));
        instance.add( new StudentGrade("943406","Oren Marquez",7));
        instance.add( new StudentGrade("701925","Todd Manning",20));
        instance.add( new StudentGrade("703427","Howard Bailey",19));
        instance.add( new StudentGrade("886938","Robert Pacheco",18));
        instance.add( new StudentGrade("431435","Myles Bryant",14));
        instance.add( new StudentGrade("142834","Ashton Frederick",17));
        instance.add( new StudentGrade("556591","Carissa Rhodes",6));
        instance.add( new StudentGrade("897729","Summer Vaughan",18));
        instance.add( new StudentGrade("695196","Tatiana Myers",13));
        instance.add( new StudentGrade("233659","Naomi Bolton",11));
        instance.add( new StudentGrade("559945","Jonah Benson",13));
        instance.add( new StudentGrade("510565","Roth Farley",16));
        instance.add( new StudentGrade("925992","Bell Stark",16));
        instance.add( new StudentGrade("872631","Robin Orr",12));
        instance.add( new StudentGrade("281419","Carissa Glover",17));
        instance.add( new StudentGrade("676179","Brandon Benjamin",12));
        instance.add( new StudentGrade("197896","Sheila Gillespie",17));
        instance.add( new StudentGrade("896739","Blair Mendez",6));
        instance.add( new StudentGrade("185027","Blythe Blevins",17));
        instance.add( new StudentGrade("864000","Stella Dalton",13));
        instance.add( new StudentGrade("636919","Justin Donovan",18));
        instance.add( new StudentGrade("142324","Wallace Trevino",13));
        instance.add( new StudentGrade("477966","Yoshio Lott",14));
        instance.add( new StudentGrade("818718","Rajah Sweet",9));
        instance.add( new StudentGrade("240007","Dorothy Carney",11));
        instance.add( new StudentGrade("195329","Rigel Fisher",18));
        instance.add( new StudentGrade("765963","Bertha Marquez",14));
        instance.add( new StudentGrade("673128","Travis Cleveland",6));
        instance.add( new StudentGrade("759809","Murphy Wiggins",11));
        instance.add( new StudentGrade("128988","Colt Wells",17));
        instance.add( new StudentGrade("707759","Bradley Reyes",16));
        instance.add( new StudentGrade("329176","Sage Brennan",15));
        instance.add( new StudentGrade("780773","Odette Christensen",11));
        instance.add( new StudentGrade("344717","Isabelle Cole",20));
        instance.add( new StudentGrade("832940","Odette Clemons",19));
        instance.add( new StudentGrade("418820","MacKenzie Padilla",18));
        instance.add( new StudentGrade("615142","Beau Frost",18));
        instance.add( new StudentGrade("861086","Tatiana Hubbard",10));
        instance.add( new StudentGrade("799968","Susan Slater",9));
        instance.add( new StudentGrade("863662","Hayden Guerra",14));
        instance.add( new StudentGrade("551609","Eleanor Bradley",14));
        instance.add( new StudentGrade("580388","Desiree Cochran",9));
        instance.add( new StudentGrade("829747","Ralph Long",16));
        instance.add( new StudentGrade("251431","Anastasia Mendez",10));
        instance.add( new StudentGrade("271691","Brandon Hartman",13));


        return instance;
    }
}
