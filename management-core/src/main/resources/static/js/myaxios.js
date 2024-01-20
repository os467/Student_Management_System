// http request拦截器 添加一个请求拦截器
axios.interceptors.request.use(function (config) {

    //token的session方案，已修改为httponly cookie携带
    /*    let token = sessionStorage.getItem("token")
    if (token != null) {
        //将token放到请求头发送给服务器
        config.headers.authentication = token;
        return config;
    }*/
    return config;
}, function (error) {
    // Do something with request error
    return Promise.reject(error);
});