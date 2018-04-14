/**
 * Created by Renato on 24/03/2018.
 */
function refreshTable() {
    var table = document.getElementById("table");
    deleteRows(table, table.rows.length - 1);

    var xmltype = new XMLHttpRequest();
    xmltype.onreadystatechange = function () {
        var jsonObj = JSON.parse(this.responseText);
        if (this.readyState==3) {
            for (i = 0; i < jsonObj.length; i++) {
                var row = table.insertRow(table.rows.length);
                var cell = row.insertCell(0);
                cell.innerHTML = jsonObj[i].method;
                cell = row.insertCell(1);
                cell.innerHTML = jsonObj[i].timestamp;
                cell = row.insertCell(2);
                cell.innerHTML = jsonObj[i].server;
                cell = row.insertCell(3);
                cell.innerHTML = jsonObj[i].refer;
                cell = row.insertCell(4);
                cell.innerHTML = jsonObj[i].url;
                cell = row.insertCell(5);
                cell.innerHTML = jsonObj[i].data;
            }
        }
    }
    xmltype.open("GET", "logRegister.json", true);
    xmltype.send();
}

function deleteRows(table, quantity) {
    for (var x = quantity; x > 0; x--) {
        table.deleteRow(x);
    }
}

$(document).ready(function () {
    refreshTable();
});