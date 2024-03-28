<#import "macros/common.ftl" as com>

<@com.common_macros "All user page">
    <#list users as user>
        <@com.user_macros user/>
        <hr>
    </#list>
</@com.common_macros>
