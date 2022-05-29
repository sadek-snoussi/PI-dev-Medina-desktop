/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pidev.edu.souk.entities;


import java.util.Date;

import java.util.Collection;

/**
 *
 * @author amalb
 */
public class User {
     private Integer id;
    private String username;
    private String usernameCanonical;
    private String email;
    private String emailCanonical;
    private boolean enabled;
    private String salt;
    private String password;
    private Date lastLogin;
    private String confirmationToken;
    private Date passwordRequestedAt;
    private String roles;
    private String nomUser;
    private String prenomUser;
    private Date dateNaissUser;
    private String mailUser;
    private String telUser;
    private String specialitePart;
    private Double popularitePart;
    private String surnomFree;
    private String urlPhotoFree;
    private String gradePro;
    private String nomEntreprisePro;
    private String telBureauPro;
    private String urlLogoPro;
    private String typeUser;
    private Integer partenariat;
    private String adresse;
    private Integer nbrPointFidelite;
    private Collection<Rating> ratingCollection;
    private Collection<Videodiy> videodiyCollection;
    private Collection<Stand> standCollection;
    private Collection<Commentaire> commentaireCollection;
    private Collection<Produit> produitCollection;
    private Collection<UserEvent> userEventCollection;
    private Collection<Commande> commandeCollection;
    private Collection<Tags> tagsCollection;
    private Collection<Panier> panierCollection;

    public Collection<Produit> getProduitCollection() {
        return produitCollection;
    }

    public void setProduitCollection(Collection<Produit> produitCollection) {
        this.produitCollection = produitCollection;
    }
    

    
    
    public User() {
    }
    
    @Override
    public String toString() {
        return "User{" + "id=" + id + ", username=" + username + ", usernameCanonical=" + usernameCanonical + ", email=" + email + ", emailCanonical=" + emailCanonical + ", enabled=" + enabled + ", salt=" + salt + ", password=" + password + ", lastLogin=" + lastLogin + ", confirmationToken=" + confirmationToken + ", passwordRequestedAt=" + passwordRequestedAt + ", roles=" + roles + ", nomUser=" + nomUser + ", prenomUser=" + prenomUser + ", dateNaissUser=" + dateNaissUser + ", mailUser=" + mailUser + ", telUser=" + telUser + ", specialitePart=" + specialitePart + ", popularitePart=" + popularitePart + ", surnomFree=" + surnomFree + ", urlPhotoFree=" + urlPhotoFree + ", gradePro=" + gradePro + ", nomEntreprisePro=" + nomEntreprisePro + ", telBureauPro=" + telBureauPro + ", urlLogoPro=" + urlLogoPro + ", typeUser=" + typeUser + ", partenariat=" + partenariat + ", adresse=" + adresse + ", ratingCollection=" + ratingCollection + ", videodiyCollection=" + videodiyCollection + ", standCollection=" + standCollection + ", commentaireCollection=" + commentaireCollection + ", produitCollection=" + produitCollection + ", userEventCollection=" + userEventCollection + ", commandeCollection=" + commandeCollection + ", tagsCollection=" + tagsCollection + ", panierCollection=" + panierCollection + '}';
    }


    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getUsernameCanonical() {
        return usernameCanonical;
    }

    public void setUsernameCanonical(String usernameCanonical) {
        this.usernameCanonical = usernameCanonical;
    }

    public String getEmailCanonical() {
        return emailCanonical;
    }

    public void setEmailCanonical(String emailCanonical) {
        this.emailCanonical = emailCanonical;
    }

    public boolean isEnabled() {
        return enabled;
    }

    public void setEnabled(boolean enabled) {
        this.enabled = enabled;
    }

    
    
    
    
    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getConfirmationToken() {
        return confirmationToken;
    }

    public void setConfirmationToken(String confirmationToken) {
        this.confirmationToken = confirmationToken;
    }

    public String getRoles() {
        return roles;
    }

    public void setRoles(String roles) {
        this.roles = roles;
    }

    public String getNomUser() {
        return nomUser;
    }

    public void setNomUser(String nomUser) {
        this.nomUser = nomUser;
    }

    public String getPrenomUser() {
        return prenomUser;
    }

    public void setPrenomUser(String prenomUser) {
        this.prenomUser = prenomUser;
    }

    public Date getDateNaissUser() {
        return dateNaissUser;
    }

    public void setDateNaissUser(Date dateNaissUser) {
        this.dateNaissUser = dateNaissUser;
    }

   

    public String getMailUser() {
        return mailUser;
    }

    public void setMailUser(String mailUser) {
        this.mailUser = mailUser;
    }

    public String getTelUser() {
        return telUser;
    }

    public void setTelUser(String telUser) {
        this.telUser = telUser;
    }

  

    public String getSpecialitePart() {
        return specialitePart;
    }

    public void setSpecialitePart(String specialitePart) {
        this.specialitePart = specialitePart;
    }

    public Double getPopularitePart() {
        return popularitePart;
    }

    public void setPopularitePart(Double popularitePart) {
        this.popularitePart = popularitePart;
    }

    public String getSurnomFree() {
        return surnomFree;
    }

    public void setSurnomFree(String surnomFree) {
        this.surnomFree = surnomFree;
    }

    public String getUrlPhotoFree() {
        return urlPhotoFree;
    }

    public void setUrlPhotoFree(String urlPhotoFree) {
        this.urlPhotoFree = urlPhotoFree;
    }

    public String getGradePro() {
        return gradePro;
    }

    public void setGradePro(String gradePro) {
        this.gradePro = gradePro;
    }

    public String getNomEntreprisePro() {
        return nomEntreprisePro;
    }

    public void setNomEntreprisePro(String nomEntreprisePro) {
        this.nomEntreprisePro = nomEntreprisePro;
    }

    public String getTelBureauPro() {
        return telBureauPro;
    }

    public void setTelBureauPro(String telBureauPro) {
        this.telBureauPro = telBureauPro;
    }

    public String getUrlLogoPro() {
        return urlLogoPro;
    }

    public void setUrlLogoPro(String urlLogoPro) {
        this.urlLogoPro = urlLogoPro;
    }

    public String getTypeUser() {
        return typeUser;
    }

    public void setTypeUser(String typeUser) {
        this.typeUser = typeUser;
    }

    public Integer getPartenariat() {
        return partenariat;
    }

    public void setPartenariat(Integer partenariat) {
        this.partenariat = partenariat;
    }

    public String getAdresse() {
        return adresse;
    }

    public void setAdresse(String adresse) {
        this.adresse = adresse;
    }

    
    public Integer getNbrPointFidelite() {
        return nbrPointFidelite;
    }

    public void setNbrPointFidelite(Integer nbrPointFidelite) {
        this.nbrPointFidelite = nbrPointFidelite;
    }

    public String getSalt() {
        return salt;
    }

    public void setSalt(String salt) {
        this.salt = salt;
    }

    public Date getLastLogin() {
        return lastLogin;
    }

    public void setLastLogin(Date lastLogin) {
        this.lastLogin = lastLogin;
    }
    
    
    
    
}
