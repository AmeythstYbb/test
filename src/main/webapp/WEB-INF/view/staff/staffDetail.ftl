<div class="pageContent">
    <form method="post" action="<#if (staff.id)??>/staff/update<#else>/staff/insert</#if>"
          class="pageForm required-validate" onsubmit="return validateCallback(this, navTabAjaxDone);">
        <div class="pageFormContent" layoutH="56">
            <h2>基本信息</h2>
            <p>
                <label>姓 名：</label>
                <input name="id" type="text" value="${(staff.id)!}" hidden="hidden"/>
                <input name="userName" class="required" type="text" size="30" value="${(staff.userName)!}" maxlength="30"/>
            </p>
            <p>
                <label>性别</label>
                <select name="gender">
                <#if ageMap??>
                    <#list ageMap?keys as itemKey>
                        <#if (staff.gender)?? && itemKey==(staff.gender)?string>
                            <option value="${itemKey}" selected="selected">${ageMap[itemKey]}</option>
                        <#else>
                            <option value="${itemKey}">${ageMap[itemKey]}</option>
                        </#if>
                    </#list>
                </#if>
                </select>
            </p>
            <p>
                <label>手机号：</label>
                <input name="mobile" class="required phone" type="text" size="30" value="${(staff.mobile)!}" maxlength="30"/>
            </p>
            <p>
                <label>出生日期：</label>
                <input type="text" name="birthday" class="date" size="30"
                       value="${(staff.birthday?string('yyyy-MM-dd'))!}"/><a class="inputDateButton"
                                                                             href="javascript:;"></a>
            </p>
            <p>
                <label>年龄：</label>
                <input type="text" value="${(staff.age)!}" name="age" class="digits" maxlength="5">
            </p>
            <p>
                <label>婚姻状况:</label>
                <select name="maritalStatus">
                <#if maritalMap??>
                    <#list maritalMap?keys as itemKey>
                        <#if (staff.maritalStatus)??&& itemKey==(staff.maritalStatus?string)>
                            <option value="${itemKey}" selected="selected">${maritalMap[itemKey]}</option>
                        <#else>
                            <option value="${itemKey}">${maritalMap[itemKey]}</option>
                        </#if>
                    </#list>
                </#if>
                </select>
            </p>
            <p>
                <label>民族：</label>
                <input type="text" value="${(staff.nation)!}" name="nation" class="textInput" maxlength="50">
            </p>
            <p>
                <label>政治面貌:</label>
                <select name="politicalStatus">
                <#if politicalMap??>
                    <#list politicalMap?keys as itemKey>
                        <option value="${itemKey}"
                                <#if (staff.politicalStatus)??&&(itemKey==(staff.politicalStatus?string))>selected="selected"</#if>>${politicalMap[itemKey]}</option>
                    </#list>
                </#if> </select>
            </p>
            <p>
                <label>证件类型：</label>
                <select name="certificateType">
                <#if certificateMap??>
                    <#list certificateMap?keys as itemKey>
                        <option value="${itemKey}"
                                <#if (staff.certificateType)??&&(itemKey==(staff.certificateType?string))>selected="selected"</#if>>${certificateMap[itemKey]}</option>
                    </#list>
                </#if> </select>
            </p>
            <p>
                <label>证件号：</label>
                <input type="text" value="${(staff.certificateNo)!}" name="certificateNo" class="textInput" maxlength="50">
            </p>
            <p>
                <label>户籍性质：</label>
                <input name="householdType" type="text" size="30" value="${(staff.householdType)!}" maxlength="30"/>
            </p>
            <p>
                <label>户籍所在地：</label>
                <input type="text" value="${(staff.household)!}" name="household" class="textInput" maxlength="40">
            </p>
            <p>
                <label>户口地址：</label>
                <input name="householdAddress" type="text" size="30" value="${(staff.householdAddress)!}" maxlength="40"/>
            </p>
            <p>
                <label>现住址：</label>
                <input type="text" value="${(staff.address)!}" name="address" class="textInput" maxlength="50">
            </p>
            <p>
                <label>学历：</label>
                <select name="education">
                <#if educationMap??>
                    <#list educationMap?keys as itemKey>
                        <option value="${itemKey}"
                                <#if (staff.education)??&&(itemKey==(staff.education?string))>selected="selected"</#if>>${educationMap[itemKey]}</option>
                    </#list>
                </#if> </select>
            </p>
            <p>
                <label>毕业日期：</label>
                <input type="text" name="graduationDate" value="${(staff.graduationDate?string('yyyy-MM-dd'))!}"
                       class="date" size="30"/><a class="inputDateButton" href="javascript:;">选择</a>
            </p>
            <p>
                <label>毕业院校：</label>
                <input name="graduatedSchool" type="text" size="30" value="${(staff.graduatedSchool)!}" maxlength="40"/>
            </p>
            <p>
                <label>专业名称：</label>
                <input name="profession" type="text" size="30" value="${(staff.profession)!}" maxlength="30"/>
            </p>
            <div class="divider"></div>
            <h2>职业信息</h2>
            <p>
                <label>员工号：</label>
            <#if staff?? && staff.jobNumber??>
                <label>${(staff.jobNumber)!}</label>
            <#--<input name="jobNumber" type="text" class="required" size="30" value="${(staff.jobNumber)!}"/>-->
            <#else>
                <input name="jobNumber" type="text" class="required" size="30" value="${(staff.jobNumber)!}" maxlength="20"/>
            </#if>
            </p>
            <p>
                <label>员工性质：</label>
                <select name="kind">
                <#if kindMap??>
                    <#list kindMap?keys as itemKey>
                        <option value="${itemKey}"
                                <#if (staff.kind)??&&(itemKey==(staff.kind?string))>selected="selected"</#if>>${kindMap[itemKey]}</option>
                    </#list>
                </#if>
                </select>
            </p>
            <p>
                <label>在职状态:</label>
                <select name="status" class="required">
                <#if statusMap??>
                    <#list statusMap?keys as itemKey>
                        <option value="${itemKey}"
                                <#if (staff.status)??&&(itemKey==(staff.status?string))>selected="selected"</#if>>${statusMap[itemKey]}</option>
                    </#list>
                </#if>
                </select>
            </p>
            <p>
                <label>工作地点：</label>
                <input name="workAddress" type="text" size="30" value="${(staff.workAddress)!}" maxlength="40"/>
            </p>
            <p>
                <label>部 门：</label>
            <#--<input name="deptId" type="text" size="30" value="${(staff.deptId)!}"/>-->
                <select name="deptId" class="required" onchange="getDept($(this).val())">
                    <option value="">请选择</option>
                <#if deptBoList??>
                    <#list deptBoList as dept>
                        <#if (staff.deptId)?? && (dept.id)! ==(staff.deptId)!>
                            <option value="${dept.id}"
                                    selected="selected">${dept.name!}
                            </option>
                        <#else>
                            <option value="${dept.id}">${dept.name!}</option>
                        </#if>
                    </#list>
                <#else>
                    <option value="">请选择</option>
                </#if>
                </select>
            </p>
            <p>
                <label>子部门：</label>
            <#--<input name="" type="text" size="30" value="${(staff.deptId)!}"/>-->
                <select id="deptChildren" name="deptChildrenId" class="required">
                <#if deptChildrenBoList??>
                    <#list deptChildrenBoList as deptChildren>
                        <#if (staff.deptChildrenId)?? && (deptChildren.id)! ==(staff.deptChildrenId)!>
                            <option value="${deptChildren.id}"
                                    selected="selected">${deptChildren.name!}
                            </option>
                        <#else>
                            <option value="${deptChildren.id}">${deptChildren.name!}</option>
                        </#if>
                    </#list>
                <#else>
                    <option value="">请选择</option>
                </#if>
                </select>
            </p>
            <p>
                <label>职 位：</label>
                <input name="position" type="text" size="30" value="${(staff.position)!}" maxlength="30"/>
            </p>
            <p>
                <label>级 别：</label>
                <input name="staffLevel" type="text" size="30" value="${(staff.staffLevel)!}" maxlength="25"/>
            </p>
            <p>
                <label>入职日期：</label>
                <input type="text" name="entryDate" value="${(staff.entryDate?string('yyyy-MM-dd'))!}" class="date"
                       size="30"/><a class="inputDateButton" href="javascript:;">选择</a>
            </p>
            <p>
                <label>转正日期：</label>
                <input type="text" name="postDate" class="date" value="${(staff.postDate?string('yyyy-MM-dd'))!}"
                       size="30"/><a class="inputDateButton" href="javascript:;">选择</a>
            </p>
            <p>
                <label>离职日期：</label>
                <input type="text" name="departureDate" value="${(staff.departureDate?string('yyyy-MM-dd'))!}"
                       class="date" size="30"/><a class="inputDateButton" href="javascript:;">选择</a>
            </p>
            <p>
                <label>合同状态:</label>
                <select name="contractType">
                <#if contractTypeMap??>
                    <#list contractTypeMap?keys as itemKey>
                        <option value="${itemKey}"
                                <#if (staff.contractType)??&&(itemKey==(staff.contractType?string))>selected="selected"</#if>>${contractTypeMap[itemKey]}</option>
                    </#list>
                </#if> </select>
            </p>
            <p>
                <label>合同起始日：</label>
                <input type="text" name="contractStartDate" class="date"
                       value="${(staff.contractStartDate?string('yyyy-MM-dd'))!}" size="30"/><a class="inputDateButton"
                                                                                                href="javascript:;">选择</a>
            </p>
            <p>
                <label>合同终止日：</label>
                <input type="text" name="contractEndDate" class="date"
                       value="${(staff.contractEndDate?string('yyyy-MM-dd'))!}" size="30"/><a class="inputDateButton"
                                                                                              href="javascript:;">选择</a>
            </p>
            <p>
                <label>司龄：</label>
                <input name="companyAge" type="text" size="30" value="${(staff.companyAge)!}" maxlength="30" class="digits"/>
            </p>
            <p>
                <label>工龄：</label>
                <input name="workAge" type="text" size="30" value="${(staff.workAge)!}" maxlength="20" class="digits"/>
            </p>
            <p>
                <label>年假天数：</label>
                <input name="annualLeaveDays" type="text" size="30" value="${(staff.annualLeaveDays)!}" maxlength="10" class="digits"/>
            </p>
            <p>
                <label>五险缴费截止日期：</label>
                <input type="text" name="fiveInsuranceEndDate" class="date"
                       value="${(staff.fiveInsuranceEndDate?string('yyyy-MM-dd'))!}" size="30"/><a
                    class="inputDateButton"
                    href="javascript:;">选择</a>
            </p>
            <p>
                <label>公积金缴费截止日期：</label>
                <input type="text" name="housingFundEndDate" class="date"
                       value="${(staff.housingFundEndDate?string('yyyy-MM-dd'))!}" size="30"/><a class="inputDateButton"
                                                                                                 href="javascript:;">选择</a>
            </p>
            <p>
                <label>招聘渠道：</label>
                <select name="recruitChannel">
                <#if recruitChannelMap??>
                    <#list recruitChannelMap?keys as itemKey>
                        <option value="${itemKey}"
                                <#if (staff.recruitChannel)??&&(itemKey==(staff.recruitChannel?string))>selected="selected"</#if>>${recruitChannelMap[itemKey]}</option>
                    </#list>
                </#if>
                </select>
            </p>
            <p>
                <label>介绍人：</label>
                <input name="introducer" type="text" size="30" value="${(staff.introducer)!}" maxlength="20"/>
            </p>
            <p>
                <label>银行卡号：</label>
                <input name="debitCardNo" type="text" size="30" value="${(staff.debitCardNo)!}" maxlength="40"/>
            </p>
            <div class="divider"></div>
            <h2>其他信息</h2>
            <p>
                <label>配偶：</label>
                <input name="spouse" type="text" size="30" value="${(staff.spouse)!}" maxlength="20"/>
            </p>
            <p>
                <label>配偶生日：</label>
                <input type="text" name="spouseBirthday" class="date"
                       value="${(staff.spouseBirthday?string('yyyy-MM-dd'))!}" size="30"/><a class="inputDateButton"
                                                                                             href="javascript:;">选择</a>
            </p>
            <p>
                <label>配偶电话：</label>
                <input name="spouseMobile" type="text" size="30" value="${(staff.spouseMobile)!}" maxlength="20"/>
            </p>
            <p>
                <label>子女：</label>
                <input name="childrenName" type="text" size="30" maxlength="20" value="${(staff.childrenName)!}"/>
            </p>
            <p>
                <label>子女生日：</label>
                <input type="text" name="childrenBirthday" value="${(staff.childrenBirthday?string('yyyy-MM-dd'))!}" class="date" size="30"/><a class="inputDateButton" href="javascript:;">选择</a>
            </p>
            <p>
                <label>子女性别：</label>
                <select name="childrenGender">
                <#if ageMap??>
                    <#list ageMap?keys as itemKey>
                        <option value="${itemKey}"
                                <#if (staff.childrenGender)??&&(itemKey==(staff.childrenGender?string))>selected="selected"</#if>>${ageMap[itemKey]}</option>
                    </#list>
                </#if>
                </select>
            </p>
            <p>
                <label>备注说明：</label>
                <input name="remark" type="text" size="30" value="${(staff.remark)!}" maxlength="50"/>
            </p>
            <p>
                <label>备注：</label>
                <input name="remark2" type="text" size="30" value="${(staff.remark2)!}" maxlength="50"/>
            </p>
        </div>
        <div class="formBar">
            <ul>
                <li>
                    <div class="buttonActive">
                        <div class="buttonContent">
                            <button type="submit">保存</button>
                        </div>
                    </div>
                </li>
                <li>
                    <div class="button">
                        <div class="buttonContent">
                            <button type="button" class="close">取消</button>
                        </div>
                    </div>
                </li>
            </ul>
        </div>
    </form>
</div>

<script>
    //部门级联js
    function getDept(id) {
        var deptChildren = $("#deptChildren");
        var initStr = "<option value=''>--请选择子部门--</option>";
        if (!id) {
            console.log("级联参数为空");
            deptChildren.html(initStr);
        }
        $.ajax({
            url: "/staff/dept/get",
            type: "post",
            dataType: "json",
            data: {id: id},
            async: false,//同步
            success: function (text) {
                //console.log(text);
                var str = "";
                for (var i = 0; i < text.length; i++) {
                    var array_element = text[i];
                    str += "<option value='" + array_element.id + "'>" + array_element.name + "</option>";
                    //console.debug(str);
                }
                deptChildren.html(initStr + str);
            }
        });
    }
</script>