//package tn.esprit.tp1_ghodbani_abdessalem_4twin_7.enities;
//
//import jakarta.persistence.*;
//import lombok.Getter;
//import lombok.Setter;
//
//import java.io.Serializable;
//
//@Getter
//@Setter
////pour generer les gettres et les setters et nous somme pas a les ecriver il sont generer maintenand et on peux accéder a ses attributs
//@Entity
//@Table(name = "User")
//public class User implements Serializable {
//    // serializable pour assurer que les données seront enregister dans la base avec leur valeur propre sans chagement ---- fiabilité c'est la phenomene de serialization
//
//    @Id
//    @GeneratedValue(strategy = GenerationType.IDENTITY)
//    si je met auto ou bien table c'est hybernet qui va prend en charge l'incrementation
//    //si je met identity,sequence c'est l sql qui va gerer l'incrementation
//    private int userId;
//
//    private String name;
//    //private String email;
//    private int tel;
//
//
//
//}
