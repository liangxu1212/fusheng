package diary.dao;

import diary.bean.Images;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.SessionFactory;

import javax.annotation.Resource;

/**
 * Created by MSI on 2017/11/12.
 */
public class ImageDao {
    private SessionFactory sessionFactory;

    @Resource
    public void setSessionFactory(SessionFactory sessionFactory){
        this.sessionFactory=sessionFactory;
    }
    public SessionFactory getSessionFactory() throws HibernateException {
        return sessionFactory;
    }

    private Session getSession() {
        return sessionFactory.getCurrentSession();
    }

    public void addImages(Images images){
        this.getSession().save(images);
    }
    public void updateImages(Images images){
        this.getSession().saveOrUpdate(images);
    }
}
