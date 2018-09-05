package ${targetPackage}.controller;

import ${targetPackage}.bean.${Bean};
import com.tmdaq.common.PageUtil;
import com.tmdaq.common.PageVo;
import com.tmdaq.common.ResultDO;
import ${targetPackage}.service.${Bean}Service;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/${bean}")
public class ${Bean}Controller {
    @Autowired
    ${Bean}Service ${bean}Service;

    @GetMapping("/{id}")
    public ${Bean} selectByPrimaryKey(@PathVariable(name = "id") Integer id) {
        return ${bean}Service.selectByPrimaryKey(id);
    }

    @GetMapping
    public ResultDO<PageUtil<${Bean}>> listPage${Bean}Bean(${Bean} ${bean}, PageUtil<${Bean}> pager) {
        return ${bean}Service.listPage${Bean}Bean(new PageVo<>(${bean}, pager != null ? pager : new PageUtil<${Bean}>()));
    }

    @PostMapping("/id")
    public ResultDO<${Bean}> insertSelective(@PathVariable("id") Integer id, ${Bean} ${bean}) {
        return ${bean}Service.insertSelective((${bean} != null ? ${bean} : new ${Bean}()).setId(id));
    }

    @PutMapping("/id")
    public ResultDO<${Bean}> updateByPrimaryKeySelective(@PathVariable("id") Integer id, ${Bean} ${bean}) {
        return ${bean}Service.updateByPrimaryKeySelective((${bean} != null ? ${bean} : new ${Bean}()).setId(id));
    }

    @DeleteMapping("/id")
    public ResultDO<${Bean}> delete(@PathVariable("id") Integer id) {
        return ${bean}Service.deleteByPrimaryKey(id);
    }
}
