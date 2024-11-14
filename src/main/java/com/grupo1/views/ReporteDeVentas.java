package com.grupo1.views;

/**
 *
 * @author Abdiel
 */
public class ReporteDeVentas extends javax.swing.JPanel {

    public ReporteDeVentas() {
        initComponents();
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        pnlPantalla = new javax.swing.JPanel();
        pnlMasVendidos = new javax.swing.JPanel();
        jScrollPane1 = new javax.swing.JScrollPane();
        tableMasVendidos = new javax.swing.JTable();
        pnlUltimas = new javax.swing.JPanel();
        jScrollPane2 = new javax.swing.JScrollPane();
        tableUltimas = new javax.swing.JTable();

        setBackground(new java.awt.Color(255, 255, 255));

        pnlPantalla.setBackground(new java.awt.Color(255, 255, 255));

        pnlMasVendidos.setBackground(new java.awt.Color(255, 255, 255));
        pnlMasVendidos.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Productos más vendidos", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Segoe UI Semibold", 0, 14))); // NOI18N

        tableMasVendidos.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Producto", "Total Vendido", "Stock Restante"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false, false
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        jScrollPane1.setViewportView(tableMasVendidos);
        if (tableMasVendidos.getColumnModel().getColumnCount() > 0) {
            tableMasVendidos.getColumnModel().getColumn(0).setResizable(false);
            tableMasVendidos.getColumnModel().getColumn(1).setResizable(false);
            tableMasVendidos.getColumnModel().getColumn(2).setResizable(false);
        }

        javax.swing.GroupLayout pnlMasVendidosLayout = new javax.swing.GroupLayout(pnlMasVendidos);
        pnlMasVendidos.setLayout(pnlMasVendidosLayout);
        pnlMasVendidosLayout.setHorizontalGroup(
            pnlMasVendidosLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnlMasVendidosLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jScrollPane1)
                .addContainerGap())
        );
        pnlMasVendidosLayout.setVerticalGroup(
            pnlMasVendidosLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnlMasVendidosLayout.createSequentialGroup()
                .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 267, Short.MAX_VALUE)
                .addContainerGap())
        );

        pnlUltimas.setBackground(new java.awt.Color(255, 255, 255));
        pnlUltimas.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Ultimas ventas", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Segoe UI Semibold", 0, 14))); // NOI18N

        tableUltimas.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Producto", "Fecha", "Venta Total"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false, false
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        jScrollPane2.setViewportView(tableUltimas);
        if (tableUltimas.getColumnModel().getColumnCount() > 0) {
            tableUltimas.getColumnModel().getColumn(0).setResizable(false);
            tableUltimas.getColumnModel().getColumn(1).setResizable(false);
            tableUltimas.getColumnModel().getColumn(2).setResizable(false);
        }

        javax.swing.GroupLayout pnlUltimasLayout = new javax.swing.GroupLayout(pnlUltimas);
        pnlUltimas.setLayout(pnlUltimasLayout);
        pnlUltimasLayout.setHorizontalGroup(
            pnlUltimasLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnlUltimasLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jScrollPane2, javax.swing.GroupLayout.DEFAULT_SIZE, 866, Short.MAX_VALUE)
                .addContainerGap())
        );
        pnlUltimasLayout.setVerticalGroup(
            pnlUltimasLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnlUltimasLayout.createSequentialGroup()
                .addComponent(jScrollPane2, javax.swing.GroupLayout.DEFAULT_SIZE, 267, Short.MAX_VALUE)
                .addContainerGap())
        );

        javax.swing.GroupLayout pnlPantallaLayout = new javax.swing.GroupLayout(pnlPantalla);
        pnlPantalla.setLayout(pnlPantallaLayout);
        pnlPantallaLayout.setHorizontalGroup(
            pnlPantallaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnlPantallaLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(pnlPantallaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(pnlMasVendidos, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(pnlUltimas, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap())
        );
        pnlPantallaLayout.setVerticalGroup(
            pnlPantallaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnlPantallaLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(pnlMasVendidos, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(pnlUltimas, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(pnlPantalla, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(pnlPantalla, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
    }// </editor-fold>//GEN-END:initComponents


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JPanel pnlMasVendidos;
    private javax.swing.JPanel pnlPantalla;
    private javax.swing.JPanel pnlUltimas;
    private javax.swing.JTable tableMasVendidos;
    private javax.swing.JTable tableUltimas;
    // End of variables declaration//GEN-END:variables
}
