package diary.dao;

import diary.bean.Images;
import org.hibernate.HibernateException;
import org.hibernate.Query;
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
    public void deleteAll(){
        String hql="delete from Images where 1=1";
        Query q=getSession().createQuery(hql);
        q.executeUpdate();
    }
    public Images findImageByUrl(String url){
        String hql="from Images where imageUrl='"+url+"'";
        Query q=getSession().createQuery(hql);
        return (Images) q.uniqueResult();
    }
    public void updateImage(Images images){
        String hql="update Images i set tumourUrl='"+images.getTumourUrl()+"',fatUrl='"+images.getFatUrl()+"'," +
                "ultrasonicResult='"+images.getUltrasonicResult()+"',tumourResult='"+images.getTumourResult()+"'," +
                "theriomaResult='"+images.getTheriomaResult()+"' where imageUrl='"+images.getImageUrl()+"'";
        Query q=getSession().createQuery(hql);
        q.executeUpdate();
    }
}
