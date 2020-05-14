package com.example.movieviewer_project;

public class Kelompok {

    private String nama;
    private String npm;
    private int foto;

    public Kelompok(String nama, String npm, int foto) {
        this.nama = nama;
        this.npm = npm;
        this.foto = foto;
    }

    public String getNama() {
        return nama;
    }

    public void setNama(String nama) {
        this.nama = nama;
    }

    public String getNpm() {
        return npm;
    }

    public void setNpm(String npm) {
        this.npm = npm;
    }

    public int getFoto() {
        return foto;
    }

    public void setFoto(int foto) {
        this.foto = foto;
    }
}
