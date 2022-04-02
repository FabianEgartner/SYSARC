$(document).ready(function() {
    $("#fromDate").val(getFormattedDate(today()));
    $("#toDate").val(getFormattedDate(tomorrow()));
});

function today() {
    return new Date();
}

function tomorrow() {
    let today = new Date();
    let tomorrow = new Date(today);
    tomorrow.setDate(tomorrow.getDate() + 1);

    return tomorrow;
}

function getFormattedDate(date) {
    return date.getFullYear()
        + "-"
        + ("0" + (date.getMonth() + 1)).slice(-2)
        + "-"
        + ("0" + date.getDate()).slice(-2);
}