package ${targetPackage}.service;

import ${targetPackage}.bean.${Bean};
import com.tmdaq.common.PageUtil;
import com.tmdaq.common.PageVo;
import com.tmdaq.common.ResultDO;

public interface ${Bean}Service {
    ResultDO<${Bean}> deleteByPrimaryKey(Integer id);

    ${Bean} selectByPrimaryKey(Integer id);

    ResultDO<PageUtil<${Bean}>> listPage${Bean}Bean(PageVo<${Bean}> param);

    ResultDO<${Bean}> insertSelective(${Bean} record);

    ResultDO<${Bean}> updateByPrimaryKeySelective(${Bean} record);
}
