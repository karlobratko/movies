/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JPanel.java to edit this template
 */
package hr.kbratko.ui.view;

import hr.kbratko.bll.concrete.factory.UserDomainModelManagerFactory;
import hr.kbratko.bll.base.manager.model.UserDomainModelManager;
import hr.kbratko.bll.concrete.model.UserDomainModel;
import hr.kbratko.lib.ui.Borders;
import hr.kbratko.lib.ui.Messages;
import hr.kbratko.ui.MainView;
import java.util.Arrays;
import java.util.Collection;
import java.util.Objects;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JTextField;

/**
 *
 * @author kbratko
 */
public class LoginView
  extends BaseView {

  private final UserDomainModelManager _userManager =
                                       UserDomainModelManagerFactory
                                         .getManager();

  private final Collection<JTextField> _validationFields;

  /**
   * Creates new form LoginView
   *
   * @param cardContainer
   */
  public LoginView(CardContainer cardContainer) {
    super(cardContainer);
    initComponents();

    this._validationFields = Arrays.asList(tfUsername, tfPassword);
  }

  /**
   * This method is called from within the constructor to initialize the form.
   * WARNING: Do NOT modify this code. The content of this method is always
   * regenerated by the Form Editor.
   */
  @SuppressWarnings("unchecked")
  // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
  private void initComponents() {

    tfPassword = new javax.swing.JPasswordField();
    lblUsername = new javax.swing.JLabel();
    tfUsername = new javax.swing.JTextField();
    lblPassword = new javax.swing.JLabel();
    btnRegister = new javax.swing.JButton();
    btnLogin = new javax.swing.JButton();
    lblTitle = new javax.swing.JLabel();

    setBackground(new java.awt.Color(255, 255, 255));

    tfPassword.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
    tfPassword.setEchoChar('\u25cf');

    lblUsername.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
    lblUsername.setText("Username:");

    tfUsername.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
    tfUsername.setSelectedTextColor(new java.awt.Color(40, 40, 40));
    tfUsername.setSelectionColor(new java.awt.Color(230, 230, 230));

    lblPassword.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
    lblPassword.setText("Password:");

    btnRegister.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
    btnRegister.setText("Don't have an account? Register...");
    btnRegister.setToolTipText("");
    btnRegister.addActionListener(new java.awt.event.ActionListener() {
      public void actionPerformed(java.awt.event.ActionEvent evt) {
        btnRegisterActionPerformed(evt);
      }
    });

    btnLogin.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
    btnLogin.setText("Login");
    btnLogin.addActionListener(new java.awt.event.ActionListener() {
      public void actionPerformed(java.awt.event.ActionEvent evt) {
        btnLoginActionPerformed(evt);
      }
    });

    lblTitle.setFont(new java.awt.Font("Segoe UI", 0, 20)); // NOI18N
    lblTitle.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
    lblTitle.setText("LOGIN");

    javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
    this.setLayout(layout);
    layout.setHorizontalGroup(
      layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
      .addGroup(layout.createSequentialGroup()
        .addGap(478, 478, 478)
        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
          .addComponent(lblTitle, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
          .addComponent(tfPassword)
          .addGroup(javax.swing.GroupLayout.Alignment.LEADING, layout.createSequentialGroup()
            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
              .addComponent(lblUsername, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
              .addComponent(lblPassword, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
            .addGap(259, 259, 259))
          .addComponent(tfUsername, javax.swing.GroupLayout.Alignment.LEADING)
          .addGroup(layout.createSequentialGroup()
            .addGap(0, 0, Short.MAX_VALUE)
            .addComponent(btnLogin, javax.swing.GroupLayout.PREFERRED_SIZE, 112, javax.swing.GroupLayout.PREFERRED_SIZE))
          .addComponent(btnRegister, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        .addGap(478, 478, 478))
    );
    layout.setVerticalGroup(
      layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
      .addGroup(layout.createSequentialGroup()
        .addGap(207, 207, 207)
        .addComponent(lblTitle)
        .addGap(18, 18, 18)
        .addComponent(lblUsername)
        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
        .addComponent(tfUsername, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
        .addGap(28, 28, 28)
        .addComponent(lblPassword)
        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
        .addComponent(tfPassword, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
        .addGap(18, 18, 18)
        .addComponent(btnLogin, javax.swing.GroupLayout.PREFERRED_SIZE, 36, javax.swing.GroupLayout.PREFERRED_SIZE)
        .addGap(18, 18, 18)
        .addComponent(btnRegister, javax.swing.GroupLayout.PREFERRED_SIZE, 38, javax.swing.GroupLayout.PREFERRED_SIZE)
        .addContainerGap(226, Short.MAX_VALUE))
    );
  }// </editor-fold>//GEN-END:initComponents

  private void btnRegisterActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnRegisterActionPerformed
    this.defaultValidationFieldBorders();
    this.cleanValidationFields();
    this.getCardContainer().showPanel(MainView.REGISTRATION_VIEW);
  }//GEN-LAST:event_btnRegisterActionPerformed

  private void btnLoginActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnLoginActionPerformed
    if (!this.isFormValid())
      return;

    try {
      UserDomainModel user = _userManager.login(tfUsername.getText().trim(),
                                                Arrays.toString(tfPassword
                                                  .getPassword()).trim());

      if (Objects.isNull(user)) {
        this.tfPassword.setText("");
        Messages.showErrorMessage("User does not exist",
                                  "Please insert valid username and password or register to use application.");
        return;
      }

      this.cleanValidationFields();
      String cardIdentifier = user.isAdmin()
                                ? MainView.ADMIN_VIEW
                                : MainView.USER_VIEW;
      BaseUserView view = user.isAdmin()
                            ? new AdminView(user, this.getCardContainer())
                            : new UserView(user, this.getCardContainer());
      this.getCardContainer().addPanel(cardIdentifier, view);
      this.getCardContainer().showPanel(cardIdentifier);
    }
    catch (Exception ex) {
      this.cleanValidationFields();
      Messages.showErrorMessage("Error occured", ex.getMessage());
      Logger.getLogger(LoginView.class.getName()).log(Level.SEVERE, null, ex);
    }
  }//GEN-LAST:event_btnLoginActionPerformed


  // Variables declaration - do not modify//GEN-BEGIN:variables
  private javax.swing.JButton btnLogin;
  private javax.swing.JButton btnRegister;
  private javax.swing.JLabel lblPassword;
  private javax.swing.JLabel lblTitle;
  private javax.swing.JLabel lblUsername;
  private javax.swing.JPasswordField tfPassword;
  private javax.swing.JTextField tfUsername;
  // End of variables declaration//GEN-END:variables

  private boolean isFormValid() {
    boolean ok = true;

    for (JTextField field : this._validationFields) {
      String text = field.getText().trim();
      ok &= !text.isEmpty();

      if (text.isEmpty())
        Borders.setBorderError(field);
      else
        Borders.setBorderDefault(field);
    }

    return ok;
  }

  private void cleanValidationFields() {
    this._validationFields.forEach(field -> field.setText(""));
  }

  private void defaultValidationFieldBorders() {
    this._validationFields.forEach(field -> Borders.setBorderDefault(field));
  }

}
