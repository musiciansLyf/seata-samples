package io.seata.sample.repository;

import io.seata.sample.entity.Storage;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * Description：
 *
 * @author fangliangsheng
 * @date 2019-04-04
 */
public interface StorageDAO extends JpaRepository<Storage, String> {//JpaRepository支持接口规范方法名查询，IsNotNull	findByAgeNotNull	... where x.age not null

    Storage findByCommodityCode(String commodityCode);

}
