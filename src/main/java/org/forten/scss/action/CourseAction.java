package org.forten.scss.action;

import org.forten.dto.Message;
import org.forten.scss.bo.CourseBo;
import org.forten.scss.dto.qo.CourseQoForTeacher;
import org.forten.scss.dto.ro.PagedRoForEasyUI;
import org.forten.scss.dto.vo.CourseForTeacher;
import org.forten.scss.entity.Course;
import org.forten.utils.common.StringUtil;
import org.forten.utils.system.ValidateException;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;
import java.util.Date;

@Controller
public class CourseAction {
    @Resource
    private CourseBo bo;

    @PostMapping("/course")
    public @ResponseBody Message save (@RequestBody Course course){
        try {
            return bo.doSave(course);
        }catch(ValidateException e){
            return Message.error(StringUtil.join(e.getMessages(), "<br>"));
        }
    }

    @PostMapping("/course/query")
    public @ResponseBody
    PagedRoForEasyUI<CourseForTeacher> listPage(@RequestParam(defaultValue = "") String name, @RequestParam(defaultValue = "") String status,
                                                @RequestParam(defaultValue = "") @DateTimeFormat(pattern = "yyyy-MM-dd") Date begin,
                                                @RequestParam(defaultValue = "") @DateTimeFormat(pattern = "yyyy-MM-dd") Date end,
                                                @RequestParam(defaultValue = "1") int page,
                                                @RequestParam(defaultValue = "10") int rows) {
        CourseQoForTeacher qo = new CourseQoForTeacher();
        qo.setName(name);
        qo.setEnd(end);
        qo.setBegin(begin);
        qo.setStatus(status);
        qo.setPage(page);
        qo.setRows(rows);
        PagedRoForEasyUI<CourseForTeacher> ro = bo.queryBy(qo);
        return ro;
    }

}
