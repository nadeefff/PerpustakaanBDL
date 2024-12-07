/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
 */
package perpustakaanfinalproject;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JComboBox;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author LENOVO
 */
public class FrameDetail extends javax.swing.JFrame {

    Connection conn;
    Statement stmt;
    PreparedStatement pstmt;

    String driver = "org.postgresql.Driver";
    String koneksi = "jdbc:postgresql://localhost:5432/Perpustakaan";
    String user = "postgres";
    String password = "12345678";
    private PreparedStatement pst;

    /**
     * Creates new form FrameDetail
     */
    public FrameDetail() {
        initComponents();
        tampil();
        TableBuku();
        TablePetugas();
        TablePeminjaman();
    }

    private void tampil() {
        try {
            Class.forName(driver);
            String sql = "SELECT * FROM detail_peminjaman";

            try (Connection conn = DriverManager.getConnection(koneksi, user, password); Statement stmt = conn.createStatement(); ResultSet rs = stmt.executeQuery(sql)) {
                this.tblDetail.setModel(DbUtils.resultSetToTableModel(rs));

            } catch (SQLException ex) {
                Logger.getLogger(FramePetugas.class.getName()).log(Level.SEVERE, null, ex);
                JOptionPane.showMessageDialog(this, "Gagal memuat data: " + ex.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
            }

        } catch (ClassNotFoundException ex) {
            Logger.getLogger(FramePetugas.class.getName()).log(Level.SEVERE, null, ex);
            JOptionPane.showMessageDialog(this, "Driver tidak ditemukan: " + ex.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
        }
    }

    private void TableBuku() {
        String query = "SELECT id_buku, Judul, stok FROM buku";
        try {
            Class.forName(driver);

            try (Connection conn = DriverManager.getConnection(koneksi, user, password); PreparedStatement stmt = conn.prepareStatement(query); ResultSet rs = stmt.executeQuery()) {

                DefaultTableModel model = (DefaultTableModel) tblBuku.getModel();
                model.setRowCount(0); // Mengosongkan tabel sebelumnya

                while (rs.next()) {
                    model.addRow(new Object[]{
                        rs.getString("id_buku"),
                        rs.getString("Judul"),
                        rs.getInt("stok")
                    });
                }
            }
        } catch (ClassNotFoundException e) {
            System.err.println("Driver database tidak ditemukan: " + e.getMessage());
        } catch (SQLException e) {
            System.err.println("Koneksi atau query database gagal: " + e.getMessage());
        }
    }

    private void TablePetugas() {
        String query = "SELECT no_petugas, nama_petugas FROM petugas";
        try {
            // Memastikan driver database sudah terdaftar
            Class.forName(driver);

            // Membuka koneksi ke database
            try (Connection conn = DriverManager.getConnection(koneksi, user, password); PreparedStatement stmt = conn.prepareStatement(query); ResultSet rs = stmt.executeQuery()) {

                // Mendapatkan model tabel petugas
                DefaultTableModel model = (DefaultTableModel) tblPetugas.getModel();
                model.setRowCount(0); // Reset tabel sebelum menambahkan data

                // Menambahkan data ke tabel petugas
                while (rs.next()) {
                    model.addRow(new Object[]{
                        rs.getString("no_petugas"),
                        rs.getString("nama_petugas")
                    });
                }
            }
        } catch (ClassNotFoundException e) {
            System.err.println("Driver database tidak ditemukan: " + e.getMessage());
        } catch (SQLException e) {
            System.err.println("Koneksi atau query database gagal: " + e.getMessage());
        }
    }

    private void TablePeminjaman() {
        String query = "SELECT kd_peminjaman, no_anggota FROM peminjaman"; // Query untuk mengambil data peminjaman
        try {
            // Memastikan driver database sudah terdaftar
            Class.forName(driver);

            // Membuka koneksi ke database
            try (Connection conn = DriverManager.getConnection(koneksi, user, password); PreparedStatement stmt = conn.prepareStatement(query); ResultSet rs = stmt.executeQuery()) {

                DefaultTableModel model = (DefaultTableModel) tblPeminjaman.getModel();
                model.setRowCount(0); // Mengosongkan tabel sebelum diisi ulang

                // Menambahkan data dari ResultSet ke model tabel
                while (rs.next()) {
                    model.addRow(new Object[]{
                        rs.getString("kd_peminjaman"),
                        rs.getString("no_anggota")
                    });
                }
            }
        } catch (ClassNotFoundException e) {
            System.err.println("Driver database tidak ditemukan: " + e.getMessage());
        } catch (SQLException e) {
            System.err.println("Koneksi atau query database gagal: " + e.getMessage());
        }
    }

    private void updateStock(String idBuku, int jumlahBuku) {
        if (jumlahBuku == 0) {
            return;
        }

        String sql = "UPDATE buku SET stok = stok + ? WHERE id_buku = ?";

        try (Connection conn = DriverManager.getConnection(koneksi, user, password); PreparedStatement pstmt = conn.prepareStatement(sql)) {

            pstmt.setInt(1, jumlahBuku);
            pstmt.setInt(2, Integer.parseInt(idBuku));

            int rowsUpdated = pstmt.executeUpdate();
            if (rowsUpdated > 0) {
                System.out.println("Stok berhasil diperbarui.");
            } else {
                JOptionPane.showMessageDialog(null, "ID Buku tidak ditemukan!", "Error", JOptionPane.ERROR_MESSAGE);
            }
        } catch (SQLException e) {
            e.printStackTrace();
            JOptionPane.showMessageDialog(null, "Gagal memperbarui stok: " + e.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
        }
    }

    private void clearFields() {
        tfJumlah.setText("");
        tfKode.setText("");
        tfPetugas.setText("");
        tfNo.setText("");
        tfTanggal.setText("");
        tfTotal.setText("");
        tfId.setText("");

    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel1 = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        jLabel6 = new javax.swing.JLabel();
        jLabel7 = new javax.swing.JLabel();
        jLabel8 = new javax.swing.JLabel();
        jLabel9 = new javax.swing.JLabel();
        tfNo = new javax.swing.JTextField();
        tfId = new javax.swing.JTextField();
        tfKode = new javax.swing.JTextField();
        tfPetugas = new javax.swing.JTextField();
        tfTanggal = new javax.swing.JTextField();
        tfJumlah = new javax.swing.JTextField();
        tfTotal = new javax.swing.JTextField();
        jScrollPane1 = new javax.swing.JScrollPane();
        tblDetail = new javax.swing.JTable();
        btnInsert = new javax.swing.JButton();
        btnUpdate = new javax.swing.JButton();
        btnDelete = new javax.swing.JButton();
        jButton6 = new javax.swing.JButton();
        jScrollPane2 = new javax.swing.JScrollPane();
        tblBuku = new javax.swing.JTable();
        jScrollPane3 = new javax.swing.JScrollPane();
        tblPetugas = new javax.swing.JTable();
        jScrollPane4 = new javax.swing.JScrollPane();
        tblPeminjaman = new javax.swing.JTable();
        cbStatus = new javax.swing.JComboBox<>();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        jPanel1.setBackground(new java.awt.Color(255, 204, 204));

        jLabel1.setFont(new java.awt.Font("Serif", 1, 24)); // NOI18N
        jLabel1.setText("DETAIL PEMINJAMAN");

        jLabel2.setFont(new java.awt.Font("Serif", 0, 14)); // NOI18N
        jLabel2.setText("Kode Peminjaman");

        jLabel3.setFont(new java.awt.Font("Serif", 0, 14)); // NOI18N
        jLabel3.setText("ID Buku");

        jLabel4.setFont(new java.awt.Font("Serif", 0, 14)); // NOI18N
        jLabel4.setText("Kode Peminjam");

        jLabel5.setFont(new java.awt.Font("Serif", 0, 14)); // NOI18N
        jLabel5.setText("No Petugas");

        jLabel6.setFont(new java.awt.Font("Serif", 0, 14)); // NOI18N
        jLabel6.setText("Tanggal Kembali");

        jLabel7.setFont(new java.awt.Font("Serif", 0, 14)); // NOI18N
        jLabel7.setText("Jumlah Buku");

        jLabel8.setFont(new java.awt.Font("Serif", 0, 14)); // NOI18N
        jLabel8.setText("Status");

        jLabel9.setFont(new java.awt.Font("Serif", 0, 14)); // NOI18N
        jLabel9.setText("Total Denda");

        tfId.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                tfIdActionPerformed(evt);
            }
        });

        tblDetail.setFont(new java.awt.Font("Serif", 0, 14)); // NOI18N
        tblDetail.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null}
            },
            new String [] {
                "Kode Peminjaman", "ID Buku", "Kode Peminjaman", "No Petugas", "Tanggal Kembali", "Jumlah Buku", "Status", "Total Denda"
            }
        ));
        tblDetail.setToolTipText("");
        tblDetail.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tblDetailMouseClicked(evt);
            }
        });
        jScrollPane1.setViewportView(tblDetail);

        btnInsert.setFont(new java.awt.Font("Serif", 0, 14)); // NOI18N
        btnInsert.setText("Insert");
        btnInsert.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnInsertActionPerformed(evt);
            }
        });

        btnUpdate.setFont(new java.awt.Font("Serif", 0, 14)); // NOI18N
        btnUpdate.setText("Update");
        btnUpdate.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnUpdateActionPerformed(evt);
            }
        });

        btnDelete.setFont(new java.awt.Font("Serif", 0, 14)); // NOI18N
        btnDelete.setText("Delete");
        btnDelete.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnDeleteActionPerformed(evt);
            }
        });

        jButton6.setFont(new java.awt.Font("Serif", 0, 14)); // NOI18N
        jButton6.setText("Back");
        jButton6.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton6ActionPerformed(evt);
            }
        });

        tblBuku.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null},
                {null, null, null},
                {null, null, null},
                {null, null, null}
            },
            new String [] {
                "ID Buku", "Judul Buku", "Stock "
            }
        ));
        tblBuku.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tblBukuMouseClicked(evt);
            }
        });
        jScrollPane2.setViewportView(tblBuku);

        tblPetugas.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null},
                {null, null},
                {null, null},
                {null, null}
            },
            new String [] {
                "Id Petugas", "Nama Petugas"
            }
        ));
        jScrollPane3.setViewportView(tblPetugas);

        tblPeminjaman.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null},
                {null, null},
                {null, null},
                {null, null}
            },
            new String [] {
                "Id Peminjaman", "No Anggota"
            }
        ));
        tblPeminjaman.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tblPeminjamanMouseClicked(evt);
            }
        });
        jScrollPane4.setViewportView(tblPeminjaman);

        cbStatus.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Dikembalikan", "DiPinjam" }));
        cbStatus.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                cbStatusMouseClicked(evt);
            }
        });

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(25, 25, 25)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel3)
                            .addComponent(jLabel2)
                            .addComponent(jLabel4)
                            .addComponent(jLabel5))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(tfId, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 149, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(tfNo, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 149, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(tfKode, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 149, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(tfPetugas, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 149, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jLabel7)
                                    .addComponent(jLabel8)
                                    .addComponent(jLabel9))
                                .addGap(67, 67, 67)
                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                    .addComponent(tfTotal, javax.swing.GroupLayout.DEFAULT_SIZE, 157, Short.MAX_VALUE)
                                    .addComponent(tfJumlah, javax.swing.GroupLayout.DEFAULT_SIZE, 157, Short.MAX_VALUE)
                                    .addComponent(cbStatus, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                                .addGap(58, 58, 58)
                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(btnDelete)
                                    .addComponent(btnUpdate)))
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addComponent(jLabel6)
                                .addGap(46, 46, 46)
                                .addComponent(tfTanggal, javax.swing.GroupLayout.PREFERRED_SIZE, 157, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(58, 58, 58)
                                .addComponent(btnInsert)))
                        .addGap(208, 208, 208))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                            .addGroup(javax.swing.GroupLayout.Alignment.LEADING, jPanel1Layout.createSequentialGroup()
                                .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 250, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(18, 18, 18)
                                .addComponent(jScrollPane3, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE)
                                .addGap(18, 18, 18)
                                .addComponent(jScrollPane4, javax.swing.GroupLayout.PREFERRED_SIZE, 267, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addComponent(jButton6)
                            .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 806, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))))
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(296, 296, 296)
                .addComponent(jLabel1)
                .addGap(0, 0, Short.MAX_VALUE))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(26, 26, 26)
                .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 28, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(47, 47, 47)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel2)
                    .addComponent(tfNo, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel6)
                    .addComponent(tfTanggal, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnInsert))
                .addGap(8, 8, 8)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(tfId, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel3)
                    .addComponent(jLabel7)
                    .addComponent(tfJumlah, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnUpdate))
                .addGap(6, 6, 6)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(tfKode, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel4)
                    .addComponent(jLabel8)
                    .addComponent(btnDelete)
                    .addComponent(cbStatus, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel5)
                    .addComponent(tfPetugas, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel9)
                    .addComponent(tfTotal, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(46, 46, 46)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 186, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jScrollPane4, javax.swing.GroupLayout.DEFAULT_SIZE, 124, Short.MAX_VALUE)
                    .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE)
                    .addComponent(jScrollPane3, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jButton6)
                .addContainerGap(20, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, 891, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void jButton6ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton6ActionPerformed
        this.dispose();
        FrameBeranda beranda = new FrameBeranda();
        beranda.setVisible(true);
    }//GEN-LAST:event_jButton6ActionPerformed

    private void btnInsertActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnInsertActionPerformed
        String kodePeminjaman = tfNo.getText();
        String idBuku = tfId.getText();          // ID buku
        String kodePeminjam = tfKode.getText();  // Kode peminjam
        String noPetugas = tfPetugas.getText();  // No Petugas
        String tanggalKembali = tfTanggal.getText(); // Tanggal kembali
        int jumlahBuku = Integer.parseInt(tfJumlah.getText()); // Jumlah buku
        String status = (String) cbStatus.getSelectedItem(); // Status dari ComboBox
        double totalDenda = Double.parseDouble(tfTotal.getText()); // Total denda

        // Validasi input untuk memastikan semua field terisi
        if (kodePeminjaman.isEmpty() || idBuku.isEmpty() || kodePeminjam.isEmpty() || noPetugas.isEmpty() || tfJumlah.getText().isEmpty() || tfTotal.getText().isEmpty()) {
            JOptionPane.showMessageDialog(null, "Semua field harus diisi!", "Error", JOptionPane.ERROR_MESSAGE);
            return;
        }

        // Perubahan stok berdasarkan status
        if (status.equals("Dipinjam")) {
            updateStock(idBuku, -jumlahBuku);  // Kurangi stok saat dipinjam
        }

        String sql = "INSERT INTO detail_peminjaman (kode_peminjaman, id_buku, kode_peminjam, no_petugas, tanggal_kembali, jumlah_buku, status, total_denda) "
                + "VALUES (?, ?, ?, ?, ?, ?, ?, ?)";

        try (Connection conn = DriverManager.getConnection(koneksi, user, password); PreparedStatement pstmt = conn.prepareStatement(sql)) {

            // Set parameter SQL query
            pstmt.setString(1, kodePeminjaman);
            pstmt.setString(2, idBuku);
            pstmt.setString(3, kodePeminjam);
            pstmt.setString(4, noPetugas);
            pstmt.setString(5, tanggalKembali);
            pstmt.setInt(6, jumlahBuku);
            pstmt.setString(7, status);
            pstmt.setDouble(8, totalDenda);

            // Eksekusi query dan cek apakah berhasil
            int rowsInserted = pstmt.executeUpdate();
            if (rowsInserted > 0) {
                JOptionPane.showMessageDialog(null, "Data berhasil ditambahkan!", "Sukses", JOptionPane.INFORMATION_MESSAGE);
            } else {
                JOptionPane.showMessageDialog(null, "Gagal menambahkan data peminjaman!", "Error", JOptionPane.ERROR_MESSAGE);
            }

            clearFields();  // Bersihkan input setelah insert
        } catch (SQLException e) {
            e.printStackTrace();
            JOptionPane.showMessageDialog(null, "Gagal menambahkan data! " + e.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
        }

        tampil();  // Menampilkan data terbaru
        TableBuku(); // Menampilkan daftar buku
    }//GEN-LAST:event_btnInsertActionPerformed

    private void tblBukuMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tblBukuMouseClicked
        tblBuku.setModel(new DefaultTableModel(
                new Object[][]{},
                new String[]{"ID Buku", "Judul", "Stock"}
        ));

    }//GEN-LAST:event_tblBukuMouseClicked

    private void tblPeminjamanMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tblPeminjamanMouseClicked
        tblPetugas.setModel(new DefaultTableModel(
                new Object[][]{},
                new String[]{"Id Peminjaman", "No Anggota"}
        ));

    }//GEN-LAST:event_tblPeminjamanMouseClicked

    private void cbStatusMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_cbStatusMouseClicked
    String[] statusOptions = {"Dipinjam", "Dikembalikan"};
    
    // Mengubah isi dari JComboBox yang sudah ada
    cbStatus.setModel(new DefaultComboBoxModel<>(statusOptions));
    }//GEN-LAST:event_cbStatusMouseClicked

    private void tblDetailMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tblDetailMouseClicked
        int selectedRow = tblDetail.getSelectedRow();

        if (selectedRow != -1) {
            // Ambil data dari kolom tabel
            String kodePeminjaman = tblDetail.getValueAt(selectedRow, 0).toString(); // Kolom 0: Kode Peminjaman
            String idBuku = tblDetail.getValueAt(selectedRow, 1).toString();        // Kolom 1: ID Buku
            String kodePeminjam = tblDetail.getValueAt(selectedRow, 2).toString();  // Kolom 2: Kode Peminjam
            String noPetugas = tblDetail.getValueAt(selectedRow, 3).toString();     // Kolom 3: No Petugas
            String tanggalKembali = tblDetail.getValueAt(selectedRow, 4).toString();// Kolom 4: Tanggal Kembali
            String jumlahBuku = tblDetail.getValueAt(selectedRow, 5).toString();    // Kolom 5: Jumlah Buku
            String status = tblDetail.getValueAt(selectedRow, 6).toString();        // Kolom 6: Status
            String totalDenda = tblDetail.getValueAt(selectedRow, 7).toString();    // Kolom 7: Total Denda

            // Set nilai ke komponen input
            tfNo.setText(kodePeminjaman);        // Kode Peminjaman ke tfKode
            tfId.setText(idBuku);                  // ID Buku ke tfId
            tfKode.setText(kodePeminjam);  // Kode Peminjam ke tfKodePeminjam
            tfPetugas.setText(noPetugas);          // No Petugas ke tfPetugas
            tfTanggal.setText(tanggalKembali);     // Tanggal Kembali ke tfTanggal
            tfJumlah.setText(jumlahBuku);          // Jumlah Buku ke tfJumlah
            cbStatus.setSelectedItem(status);      // Status ke cbStatus
            tfTotal.setText(totalDenda);           // Total Denda ke tfTotal
        }
    }//GEN-LAST:event_tblDetailMouseClicked

    private void btnDeleteActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnDeleteActionPerformed
        String kodePeminjaman = tfNo.getText();

        if (kodePeminjaman.isEmpty()) {
            JOptionPane.showMessageDialog(this, "Pilih data yang akan dihapus!", "Peringatan", JOptionPane.WARNING_MESSAGE);
            return;
        }

        try {
            String sql = "DELETE FROM detail_peminjaman WHERE kode_peminjaman = ?";

            try (Connection conn = DriverManager.getConnection(koneksi, user, password); PreparedStatement pstmt = conn.prepareStatement(sql)) {

                pstmt.setString(1, kodePeminjaman);

                int rowsDeleted = pstmt.executeUpdate();

                // Memberikan feedback kepada pengguna
                if (rowsDeleted > 0) {
                    JOptionPane.showMessageDialog(this, "Data berhasil dihapus!", "Informasi", JOptionPane.INFORMATION_MESSAGE);
                    tampil(); // Refresh tabel setelah penghapusan
                } else {
                    JOptionPane.showMessageDialog(this, "Data tidak ditemukan!", "Error", JOptionPane.ERROR_MESSAGE);
                }

            } catch (SQLException ex) {
                Logger.getLogger(FramePetugas.class.getName()).log(Level.SEVERE, null, ex);
                JOptionPane.showMessageDialog(this, "Terjadi kesalahan saat menghapus data: " + ex.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
            }

        } catch (Exception ex) {
            Logger.getLogger(FramePetugas.class.getName()).log(Level.SEVERE, null, ex);
            JOptionPane.showMessageDialog(this, "Terjadi kesalahan: " + ex.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
        }
        clearFields();
    }//GEN-LAST:event_btnDeleteActionPerformed

    private void btnUpdateActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnUpdateActionPerformed
    String kodePeminjaman = tfNo.getText();  // Kode peminjaman
    String idBuku = tfId.getText();            // ID buku
    String kodePeminjam = tfKode.getText();      // Kode peminjam
    String noPetugas = tfPetugas.getText();    // No Petugas
    String tanggalKembali = tfTanggal.getText(); // Tanggal kembali
    int jumlahBuku = Integer.parseInt(tfJumlah.getText()); // Jumlah buku
    String status = (String) cbStatus.getSelectedItem(); // Status dari ComboBox
    double totalDenda = Double.parseDouble(tfTotal.getText()); // Total denda

    if (kodePeminjaman.isEmpty() || idBuku.isEmpty() || kodePeminjam.isEmpty() || noPetugas.isEmpty() || tfJumlah.getText().isEmpty() || tfTotal.getText().isEmpty()) {
        JOptionPane.showMessageDialog(null, "Semua field harus diisi!", "Error", JOptionPane.ERROR_MESSAGE);
        return;
    }

    if (status.equals("Dipinjam")) {
        // Mengecek dan mengurangi stok buku jika dipinjam
        updateStock(idBuku, -jumlahBuku);  
    } else if (status.equals("Dikembalikan")) {
        // Menambah stok saat buku dikembalikan
        updateStock(idBuku, +jumlahBuku);  
    }

    String sql = "UPDATE detail_peminjaman SET id_buku = ?, kode_peminjam = ?, no_petugas = ?, tanggal_kembali = ?, jumlah_buku = ?, status = ?, total_denda = ? "
            + "WHERE kode_peminjaman = ?";

    try (Connection conn = DriverManager.getConnection(koneksi, user, password); PreparedStatement pstmt = conn.prepareStatement(sql)) {

        pstmt.setString(1, idBuku);
        pstmt.setString(2, kodePeminjam);
        pstmt.setString(3, noPetugas);
        pstmt.setString(4, tanggalKembali);
        pstmt.setInt(5, jumlahBuku);
        pstmt.setString(6, status);
        pstmt.setDouble(7, totalDenda);
        pstmt.setString(8, kodePeminjaman);

        int rowsUpdated = pstmt.executeUpdate();
        if (rowsUpdated > 0) {
            JOptionPane.showMessageDialog(null, "Data berhasil diperbarui!", "Sukses", JOptionPane.INFORMATION_MESSAGE);
        } else {
            JOptionPane.showMessageDialog(null, "Kode peminjaman tidak ditemukan!", "Error", JOptionPane.ERROR_MESSAGE);
        }

        clearFields();
    } catch (SQLException e) {
        e.printStackTrace();
        JOptionPane.showMessageDialog(null, "Gagal memperbarui data!", "Error", JOptionPane.ERROR_MESSAGE);
    }
    tampil();
    TableBuku();
    }//GEN-LAST:event_btnUpdateActionPerformed

    private void tfIdActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_tfIdActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_tfIdActionPerformed

    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
         */
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(FrameDetail.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(FrameDetail.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(FrameDetail.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(FrameDetail.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new FrameDetail().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnDelete;
    private javax.swing.JButton btnInsert;
    private javax.swing.JButton btnUpdate;
    private javax.swing.JComboBox<String> cbStatus;
    private javax.swing.JButton jButton6;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JScrollPane jScrollPane3;
    private javax.swing.JScrollPane jScrollPane4;
    private javax.swing.JTable tblBuku;
    private javax.swing.JTable tblDetail;
    private javax.swing.JTable tblPeminjaman;
    private javax.swing.JTable tblPetugas;
    private javax.swing.JTextField tfId;
    private javax.swing.JTextField tfJumlah;
    private javax.swing.JTextField tfKode;
    private javax.swing.JTextField tfNo;
    private javax.swing.JTextField tfPetugas;
    private javax.swing.JTextField tfTanggal;
    private javax.swing.JTextField tfTotal;
    // End of variables declaration//GEN-END:variables

}
