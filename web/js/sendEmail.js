var nodemailer = require('nodemailer');

var transporter = nodemailer.createTransport({
  service: 'gmail',
  auth: {
    user: 'SkiliftApplikation@gmail.com',
    pass: 'Skilift123'
  }
});

var mailOptions = {
  from: 'SkiliftApplikation@gmail.com',
  to: 'tkollerpriv@gmail.com',
  subject: 'Sending Email using Node.js',
  text: 'That was easy!'
};

transporter.sendMail(mailOptions, function(error, info){
  if (error) {
    console.log(error);
  } else {
    console.log('Email sent: ' + info.response);
  }
});



