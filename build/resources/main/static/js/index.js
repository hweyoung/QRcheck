var main = {
    init : function () {
        var _this = this;
        $('#btn-save').on('click', function () {
            _this.save();
        })
    },
    save : function () {
        var data = {
            uuid : $("#uuid").val(),
            pwd : $("#pwd").val(),
        };

        $.ajax({
            type : 'POST',
            url : '/user/'+ uuid +'/'+pwd,
            dataType : 'json',
            contentType : 'application/json; charset=utf-8',
            data : JSON.stringify(data)
        }).done(function () {
            alert('로그인 성공');
            window.location.href = '/';
        }).fail(function (error) {
            alert(JSON.stringify(error));
        })
    }
};

main.init();