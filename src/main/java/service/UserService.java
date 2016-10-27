package service;

import entities.User;

import javax.persistence.TypedQuery;

/**
 * Created by Steeve Sinigaglia on 17/10/2016.
 */
public class UserService extends CrudAbstractServiceImpl<User> {

    //implements all custom request for this entity here

    /**
     * Vérifie si l'adresse email est déjà présente en base
     * @param email email à vérifier
     * @return true si l'email existe, false sinon
     */
    public boolean checkEmailExist(String email){
        TypedQuery<User> query = em.createQuery("select u from User u where u.email like :mEmail", User.class);
        query.setParameter("mEmail",email);
        return query.getResultList().isEmpty() ? false : true;
    }
}
