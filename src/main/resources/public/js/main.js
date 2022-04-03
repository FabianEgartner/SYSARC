$(document).ready(function() {
    $("#fromDate").val(getFormattedDate(fromDate()));
    $("#toDate").val(getFormattedDate(toDate()));
});

function fromDate() {
    return new Date();
}

function toDate() {
    let fromDate = new Date();
    let toDate = new Date(fromDate);
    toDate.setDate(toDate.getDate() + 6);

    return toDate;
}

function getFormattedDate(date) {
    return date.getFullYear()
        + "-"
        + ("0" + (date.getMonth() + 1)).slice(-2)
        + "-"
        + ("0" + date.getDate()).slice(-2);
}