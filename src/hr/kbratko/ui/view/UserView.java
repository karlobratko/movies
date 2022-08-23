/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JPanel.java to edit this template
 */
package hr.kbratko.ui.view;

import hr.kbratko.bll.base.manager.model.MovieDomainModelManager;
import hr.kbratko.bll.concrete.factory.MovieDomainModelManagerFactory;
import hr.kbratko.bll.concrete.model.UserDomainModel;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author kbratko
 */
public class UserView
  extends BaseUserView {

  private static final MovieDomainModelManager _movieManager =
                                              MovieDomainModelManagerFactory
                                                .getManager();

  /**
   * Creates new form User
   *
   * @param loggedInUser
   * @param cardContainer
   */
  public UserView(UserDomainModel loggedInUser, CardContainer cardContainer) {
    super(loggedInUser, cardContainer);
    initComponents();

    lblUsername.setText(this.getLoggedInUser().getUsername());
  }

  /**
   * This method is called from within the constructor to initialize the form.
   * WARNING: Do NOT modify this code. The content of this method is always
   * regenerated by the Form Editor.
   */
  @SuppressWarnings("unchecked")
  // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
  private void initComponents() {

    jTabbedPane1 = new javax.swing.JTabbedPane();
    lblUsername = new javax.swing.JLabel();

    lblUsername.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
    jTabbedPane1.addTab("tab1", lblUsername);

    javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
    this.setLayout(layout);
    layout.setHorizontalGroup(
      layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
      .addComponent(jTabbedPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 1280, Short.MAX_VALUE)
    );
    layout.setVerticalGroup(
      layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
      .addComponent(jTabbedPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 720, Short.MAX_VALUE)
    );
  }// </editor-fold>//GEN-END:initComponents


  // Variables declaration - do not modify//GEN-BEGIN:variables
  private javax.swing.JTabbedPane jTabbedPane1;
  private javax.swing.JLabel lblUsername;
  // End of variables declaration//GEN-END:variables
}
