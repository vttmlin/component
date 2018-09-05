package ${targetPackage}.service;

import ${targetPackage}.bean.${Bean};
import com.tmdaq.common.PageUtil;
import com.tmdaq.common.PageVo;
import com.tmdaq.common.ResCode;
import com.tmdaq.common.ResultDO;
import ${targetPackage}.dao.${Bean}Mapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ${Bean}ServiceImpl implements ${Bean}Service {
    @Autowired
    ${Bean}Mapper ${bean}Mapper;

    public ${Bean} selectByPrimaryKey(Integer id) {
        return ${bean}Mapper.selectByPrimaryKey(id);
    }

    @Override
    public ResultDO<PageUtil<${Bean}>> listPage${Bean}Bean(PageVo<${Bean}> param) {
        return new ResultDO<>(param.getPager().setDataList(${bean}Mapper.listPage${Bean}Bean(param)));
    }

    @Override
    public ResultDO<${Bean}> insertSelective(${Bean} ${bean}) {
        ${bean}Mapper.insertSelective(${bean});
        return ${bean}.getId()!=null?new ResultDO<${Bean}>(${bean}):new ResultDO<${Bean}>(null, ResCode.ERROR);
    }

    @Override
    public ResultDO<${Bean}> updateByPrimaryKeySelective(${Bean} ${bean}) {
        ${bean}Mapper.updateByPrimaryKeySelective(${bean});
        return ${bean}.getId()!=null?new ResultDO<${Bean}>(${bean}):new ResultDO<${Bean}>(null, ResCode.ERROR);
    }

    @Override
    public ResultDO<${Bean}> deleteByPrimaryKey(Integer id) {
        ${Bean} ${bean} = selectByPrimaryKey(id);
        int i = ${bean}Mapper.deleteByPrimaryKey(${bean}.getId());
        return i==1?new ResultDO<${Bean}>(${bean}):new ResultDO<>(null,ResCode.ERROR);
    }
}
