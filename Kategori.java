/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Main.java to edit this template
 */
package perpustakaanfinalproject;

/**
 *
 * @author LENOVO
 */
public class Kategori {
    private Integer idKategori;
    private String namaKategori;

    // Constructor
    public Kategori(Integer idKategori, String namaKategori) {
        this.idKategori = idKategori;
        this.namaKategori = namaKategori;
    }

    // Getters
    public Integer getId() {
        return idKategori;
    }

    public String getNama() {
        return namaKategori;
    }

    // Optionally, override toString() for displaying in JComboBox
    @Override
    public String toString() {
        return namaKategori;  // This will be shown in the ComboBox
    }

    int getIdKategori() {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }
}
