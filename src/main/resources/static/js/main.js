$(document).ready(function() {
    // Example: Confirm delete operation
    $('form').on('submit', function() {
        return confirm('Are you sure you want to delete this user?');
    });
});
