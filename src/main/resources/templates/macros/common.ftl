<#macro common_macros title>
    <html>
    <head>
        <title>${title}</title>
    </head>
    <body>
    <#nested>
    </body>
    </html>
</#macro>

<#macro user_macros user>
    <h2>${user.id}</h2>
    <h2>${user.username}</h2>
    <h2>${user.userPassword}</h2>
    <h2>${user.created}</h2>
    <h2>${user.changed}</h2>
    <h2>${user.age}</h2>
</#macro>
